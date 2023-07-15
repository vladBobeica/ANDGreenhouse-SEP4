package com.example.greenhouse.model;

public class MeasurementModel {
    private final String date;
    private final String temperature;
    private final String humidity;
    private final String light;

    public MeasurementModel(String date, String temperature, String humidity, String light) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
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

