package com.example.EventRegistrationApp.ModelClasses;

import android.widget.Button;

public class Model {
    String name, price, date, desc;
    Button delete;

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
