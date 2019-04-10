package com.example.mri.Dessert;

public class Dessert {
    private String Name;
    private String Location;

    public Dessert() {
    }

    public Dessert(String name, String location) {
        Name = name;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
