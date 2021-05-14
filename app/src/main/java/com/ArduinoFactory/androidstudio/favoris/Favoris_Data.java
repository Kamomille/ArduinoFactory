package com.ArduinoFactory.androidstudio.favoris;


public class Favoris_Data {

    public String name;
    public int imageId;
    public String name_class;

    public Favoris_Data(String name, int imageId, String name_class) {
        this.name = name;
        this.imageId = imageId;
        this.name_class = name_class;
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

    public String getNameClass() {
        return name_class;
    }
    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

}