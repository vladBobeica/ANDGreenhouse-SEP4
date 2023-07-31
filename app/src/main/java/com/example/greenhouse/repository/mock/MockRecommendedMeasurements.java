package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.RecommendedMeasurementsModel;

import java.util.ArrayList;
import java.util.List;

public class MockRecommendedMeasurements {
    private static final List<RecommendedMeasurementsModel> measurements = new ArrayList<>();

    static {
        measurements.add(new RecommendedMeasurementsModel(1,"15", "25", "103", "110", "12", "20"));
        measurements.add(new RecommendedMeasurementsModel(2,"10", "30", "100", "110", "15", "30"));
    }

    public static  List<RecommendedMeasurementsModel> getRecommendedMeasurements() {
        return measurements;
    }
}
