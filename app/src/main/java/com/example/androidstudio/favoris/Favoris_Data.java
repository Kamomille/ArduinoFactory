package com.example.androidstudio.favoris;


public class Favoris_Data {

    public String name;
    public int imageId;


    public Favoris_Data(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}