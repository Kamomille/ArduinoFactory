package com.ArduinoFactory.androidstudio.favoris;

public class Favoris_Data {

    private final String name;
    private final int imageId;
    private final String name_class;

    public Favoris_Data(String name, int imageId, String name_class) {
        this.name = name;
        this.imageId = imageId;
        this.name_class = name_class;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getNameClass() {
        return name_class;
    }
}
