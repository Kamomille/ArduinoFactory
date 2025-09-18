package com.ArduinoFactory.androidstudio.cours;

public class CoursData {
    private String name;
    private int imageRes;

    public CoursData(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }
}


