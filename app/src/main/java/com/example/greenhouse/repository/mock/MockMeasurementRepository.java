package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.MeasurementModel;

import java.util.ArrayList;
import java.util.List;

public class MockMeasurementRepository {
    private static final List<MeasurementModel> measurements = new ArrayList<>();

    static {
        measurements.add(new MeasurementModel(1, "01/02/2022", "12", "43", "321"));
        measurements.add(new MeasurementModel(2, "21/05/2023", "30", "23", "347"));
        measurements.add(new MeasurementModel(3, "29/06/2023", "33", "28", "318"));
        measurements.add(new MeasurementModel(4, "25/07/2023", "36", "26", "332"));
    }

    public static List<MeasurementModel> getMeasurement() {
        return measurements;
    }
}
