package com.example.EventRegistrationApp.ModelClasses;

public class ModelAllReg {
    private String name;
    private String date;
    private String userName;
    private String userPhone;
    private String id;

   public ModelAllReg(String Name, String Date,String UserName, String UserPhone){
       this.name = Name;
       this.date = Date;
       this.userName = UserName;
       this.userPhone= UserPhone;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ModelAllReg(){}

    public String toString(){
       return this.id  + name + date + userName + userPhone;
    }

}
