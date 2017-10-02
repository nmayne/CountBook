package com.a301.nicholasmayne.countbook;

import java.util.Date;
import java.util.Calendar;


public class Counter {
    private String name;
    private int initialValue;
    private int value;
    private Date date;
    private String comment;

    public Counter(String name, int initialValue) {
        this.name = name;
        this.initialValue = initialValue;
        this.value = initialValue;
        this.date = Calendar.getInstance().getTime();
        this.comment = null;
    }
    public Counter(String name, int initialValue, String comment) {
        this.name = name;
        this.initialValue = initialValue;
        this.value = initialValue;
        this.date = Calendar.getInstance().getTime();
        this.comment = comment;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public int getInitialValue() {
        return this.initialValue;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public String toString(){
        return this.name + " | (" + this.value + ") \nDate: " + this.date;
    }
}
