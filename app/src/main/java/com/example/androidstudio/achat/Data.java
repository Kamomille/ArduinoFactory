package com.example.androidstudio.achat;


public class Data {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int imageId;

    Data(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}