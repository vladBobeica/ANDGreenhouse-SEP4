package com.example.greenhouse.model;

public class RecommendedMeasurementsModel {
    private int id;
    private String MinTemperature;
    private String MaxTemperature;
    private String MinHumidity;
    private String MaxHumidity;
    private String MinLight;
    private String MaxLight;

    public RecommendedMeasurementsModel(String minTemperature, String maxTemperature, String minHumidity, String maxHumidity, String minLight, String maxLight) {
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        MinHumidity = minHumidity;
        MaxHumidity = maxHumidity;
        MinLight = minLight;
        MaxLight = maxLight;
    }

    public RecommendedMeasurementsModel(int id, String minTemperature, String maxTemperature, String minHumidity, String maxHumidity, String minLight, String maxLight) {
        this.id = id;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        MinHumidity = minHumidity;
        MaxHumidity = maxHumidity;
        MinLight = minLight;
        MaxLight = maxLight;
    }

    public int getId() {
        return id;
    }

    public String getMinTemperature() {
        return MinTemperature;
    }

    public String getMaxTemperature() {
        return MaxTemperature;
    }

    public String getMinHumidity() {
        return MinHumidity;
    }

    public String getMaxHumidity() {
        return MaxHumidity;
    }

    public String getMinLight() {
        return MinLight;
    }

    public String getMaxLight() {
        return MaxLight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMinTemperature(String minTemperature) {
        MinTemperature = minTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        MaxTemperature = maxTemperature;
    }

    public void setMinHumidity(String minHumidity) {
        MinHumidity = minHumidity;
    }

    public void setMaxHumidity(String maxHumidity) {
        MaxHumidity = maxHumidity;
    }

    public void setMinLight(String minLight) {
        MinLight = minLight;
    }

    public void setMaxLight(String maxLight) {
        MaxLight = maxLight;
    }
}
