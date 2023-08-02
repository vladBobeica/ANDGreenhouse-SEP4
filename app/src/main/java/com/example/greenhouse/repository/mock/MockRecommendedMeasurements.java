package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.RecommendedMeasurementsModel;

import java.util.ArrayList;
import java.util.List;

public class MockRecommendedMeasurements {
    private static final List<RecommendedMeasurementsModel> measurements = new ArrayList<>();

    static {
        measurements.add(new RecommendedMeasurementsModel(1,"20", "20", "21", "21", "22", "22"));
        measurements.add(new RecommendedMeasurementsModel(2,"15", "15", "16", "16", "17", "17"));
        measurements.add(new RecommendedMeasurementsModel(3,"13", "13", "12", "12", "11", "11"));
        measurements.add(new RecommendedMeasurementsModel(4,"2", "2", "3", "3", "4", "4"));

    }

    public static  List<RecommendedMeasurementsModel> getRecommendedMeasurements() {
        return measurements;
    }
}
