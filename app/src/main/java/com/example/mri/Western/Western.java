package com.example.mri.Western;

public class Western {
    private String Name;
    private String Location;

    public Western() {
    }

    public Western(String name, String location) {
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
