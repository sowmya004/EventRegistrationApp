package com.example.EventRegistrationApp.ModelClasses;

public class RegList {
    private String Name;
    private String Price;
    private String Date;
    private String UserName;
    private String UserPhone;

    public RegList(String name, String price, String date, String userName,String userPhone) {
        Name = name;
        Price = price;
        Date = date;
        UserName = userName;
        UserPhone = userPhone;

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

   public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}