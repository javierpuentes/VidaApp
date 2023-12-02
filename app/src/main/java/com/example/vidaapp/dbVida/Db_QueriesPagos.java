package com.example.vidaapp.dbVida;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Db_QueriesPagos {
    private int id;
    private int user;
    private int type;
    private int year;
    private String month;
    private float consumption;
    private float price;
    private float measurer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMeasurer() {
        return measurer;
    }

    public void setMeasurer(float measurer) {
        this.measurer = measurer;
    }

}
