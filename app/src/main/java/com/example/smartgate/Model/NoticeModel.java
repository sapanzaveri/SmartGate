package com.example.smartgate.Model;

public class NoticeModel {
    private int Id;
    private String Title;
    private String Description;
    private String Date;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "NoticeModel{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
