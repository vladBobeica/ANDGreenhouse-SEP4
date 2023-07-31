package com.example.greenhouse.model;

public class GreenHouseModel {
    private int id;
    private String name;
    private String address;
    private RecommendedMeasurementsModel recommendedMeasurementsModel;

    public GreenHouseModel(String name, String address, RecommendedMeasurementsModel recommendedMeasurementsModel) {
        this.name = name;
        this.address = address;
        this.recommendedMeasurementsModel = recommendedMeasurementsModel;
    }

    public GreenHouseModel(int id, String name, String address, RecommendedMeasurementsModel recommendedMeasurementsModel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.recommendedMeasurementsModel = recommendedMeasurementsModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RecommendedMeasurementsModel getRecommendedMeasurementsModel() {
        return recommendedMeasurementsModel;
    }

    public void setRecommendedMeasurementsModel(RecommendedMeasurementsModel recommendedMeasurementsModel) {
        this.recommendedMeasurementsModel = recommendedMeasurementsModel;
    }
}
