package com.example.greenhouse.model;

public class RecommendedMeasurementsModel {
    private String title;
    private int icon;

    public RecommendedMeasurementsModel(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}
