package com.example.mri.DataModel;

public class Category {
    private String resName;
    private String Image;

    public Category() {
    }

    public Category(String resName, String image) {
        this.resName = resName;
        Image = image;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
