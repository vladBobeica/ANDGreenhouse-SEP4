package com.example.greenhouse.model;

public class MeasurementModel {
    private  int id;
    private String date;
    private String temperature;
    private String humidity;
    private String light;

    public MeasurementModel(int id, String date, String temperature, String humidity, String light) {
        this.id = id;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
    }

    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getLight() {
        return light;
    }
}

