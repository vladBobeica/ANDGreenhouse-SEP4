package com.example.greenhouse.ui.components;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenhouse.model.MeasurementModel;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseComponentViewModel extends ViewModel {

    private MutableLiveData<List<MeasurementModel>> measurementListLiveData;

    public GreenhouseComponentViewModel() {
        measurementListLiveData = new MutableLiveData<>();
        measurementListLiveData.setValue(new ArrayList<>());
    }

    public LiveData<List<MeasurementModel>> getMeasurementList() {
        return measurementListLiveData;
    }

    public void addMeasurement(MeasurementModel measurement) {
        List<MeasurementModel> measurements = measurementListLiveData.getValue();
        measurements.add(measurement);
        measurementListLiveData.setValue(measurements);
    }
}
