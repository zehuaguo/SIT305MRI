package com.example.mri.DataModel;

public class User {
    private String Id;
    private String Password;

    public User() {
    }

    public User(String id, String password) {
        Id = id;
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
