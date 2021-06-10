package com.example.smartgate.Model;

public class ComplaintModel {
    private int Id;
    private String Name;
    private String Description;
    private String IsActive;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    @Override
    public String toString() {
        return "ComplaintModel{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", IsActive='" + IsActive + '\'' +
                '}';
    }
}
