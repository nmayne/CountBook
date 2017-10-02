/*
 * Copyright (c) 2017. nmayne, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 * You may find a copy of the licence in this project. Otherwise please contact nmayne@ualberta.ca
 */

package com.a301.nicholasmayne.countbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class CountListActivity extends AppCompatActivity {


    ListView countListView;
    ArrayList<Counter> countList;
    ArrayAdapter<Counter> adapter; // manages the count list to properly show it


    Button newCount;

    EditText countName;
    EditText countInitial;

    static final String FILENAME = "file.sav";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_list);
        countListView = (ListView) findViewById(R.id.countListView);
        Button clearButton = (Button) findViewById(R.id.clearAll);


        countListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CountListActivity.this, CountDetailActivity.class);

                Counter counter = (Counter) adapterView.getItemAtPosition(i);
                intent.putExtra("COUNT_NAME", counter.getName());
                intent.putExtra("COUNT_VALUE", counter.getValue());
                intent.putExtra("COUNT_DATE", counter.getDate());
                intent.putExtra("COUNT_COMMENT", counter.getComment());

                startActivity(intent);

            }
        });

        newCount = (Button) findViewById(R.id.newCount);
        countName = (EditText) findViewById(R.id.editCountName);
        countInitial = (EditText) findViewById(R.id.editCountInitial);

        newCount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                String name = countName.getText().toString();
                int initialValue = Integer.parseInt(countInitial.getText().toString());
                countList.add(new Counter(name, initialValue));
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
            }


        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.activity_count_list_item, countList);
        countListView.setAdapter(adapter);
    }

    void clearAll() {

    }

    void loadFromFile() throws RuntimeException {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            this.countList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            this.countList = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    void saveInFile() throws RuntimeException {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.countList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
