package com.example.greenhouse.ui.viewmodel;

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

    public void addSampleData() {
        List<MeasurementModel> measurementList = new ArrayList<>();

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-13", "23°C", "70%", "800 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementListLiveData.setValue(measurementList);
    }
}




