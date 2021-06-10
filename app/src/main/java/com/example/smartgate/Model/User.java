package com.example.smartgate.Model;

public class User {
    int Id;
    String Name, User_Name, Phone_No;


    public User(int Id, String Name, String User_Name, String Phone_No) {
        this.Id = Id;
        this.Name = Name;
        this.User_Name = User_Name;
        this.Phone_No = Phone_No;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String Phone_No) {
        this.Phone_No = Phone_No;
    }
}
