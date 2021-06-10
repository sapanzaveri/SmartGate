package com.example.smartgate.Model;

public class LoginResponse {
    User user;
    String Status;

    public LoginResponse(User user, String Status) {
        this.user = user;
        this.Status = Status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
