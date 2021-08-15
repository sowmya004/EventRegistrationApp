package com.example.EventRegistrationApp.ModelClasses;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ModelDisplayReg implements Serializable {

    @Exclude
    private String id;
    private String name, date, price;

    public ModelDisplayReg(){}

    public ModelDisplayReg(String name, String price, String date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
