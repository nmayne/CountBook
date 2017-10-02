package com.a301.nicholasmayne.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CountDetailActivity extends AppCompatActivity {

    Button increaseCount;
    Button decreaseCount;
    Button resetCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_detail);

        increaseCount = (Button) findViewById(R.id.incCount);
        decreaseCount = (Button) findViewById(R.id.decCount);
        resetCount = (Button) findViewById(R.id.resetCount);

        Intent intent = getIntent();

        // Set the Count details
        TextView countName = (TextView) findViewById(R.id.countName);
        TextView countDate = (TextView) findViewById(R.id.countDate);
        TextView countComment = (TextView) findViewById(R.id.countComment);
        TextView countValue = (TextView) findViewById(R.id.countValue);

        countName.setText(intent.getStringExtra("COUNT_NAME"));
        countValue.setText(intent.getStringExtra("COUNT_VALUE"));
        countDate.setText(intent.getStringExtra("COUNT_DATE"));
        countComment.setText(intent.getStringExtra("COUNT_COMMENT"));
    }

    void incCount() {

    }

}
