package com.example.EventRegistrationApp.ModelClasses;

public class PoojaList
{
    private String Name;
    private String Price;
    private String Date;
    private String Desc;


    public PoojaList(String name, String price, String date, String desc)
    {
        Name= name;
        Price= price;
        Date= date;
        Desc= desc;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}