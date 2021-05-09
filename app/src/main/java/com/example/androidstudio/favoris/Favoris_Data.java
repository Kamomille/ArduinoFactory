package com.example.androidstudio.favoris;


public class Favoris_Data {

    public String name;
    public int imageId;
    public String lienAmazon;


    public Favoris_Data(String name, int imageId, String lienAmazon) {
        this.name = name;
        this.imageId = imageId;
        this.lienAmazon = lienAmazon;
    }

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

    public String getLien() {
        return lienAmazon;
    }
    public void setLien(String lienAmazon) {
        this.lienAmazon = lienAmazon;
    }

}