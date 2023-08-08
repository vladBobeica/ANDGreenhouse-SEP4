package com.example.greenhouse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.repository.MeasurementRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenhouseComponentViewModel extends ViewModel {

    private MeasurementRepository measurementRepository;
    private MutableLiveData<List<MeasurementModel>> measurementListLiveData;

    public GreenhouseComponentViewModel() {
        measurementRepository = new MeasurementRepository();
        measurementListLiveData = new MutableLiveData<>();
        measurementListLiveData.setValue(new ArrayList<>());
    }

    public LiveData<List<MeasurementModel>> getMeasurementList() {
        return measurementListLiveData;
    }

    public void fetchMeasurements() {
        measurementRepository.getAllMeasurements(new Callback<List<MeasurementModel>>() {
            @Override
            public void onResponse(Call<List<MeasurementModel>> call, Response<List<MeasurementModel>> response) {
                if (response.isSuccessful()) {
                    measurementListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MeasurementModel>> call, Throwable t) {

            }
        });
    }

    public void addSampleData() {
        List<MeasurementModel> measurementList = new ArrayList<>();

        measurementList.add(new MeasurementModel(1, "2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel(2, "2023-07-13", "23°C", "70%", "800 Lux"));

        measurementListLiveData.setValue(measurementList);
    }
}




