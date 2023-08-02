package com.example.greenhouse.repository;

import com.example.greenhouse.BuildConfig;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MeasurementRepository {
    private ApiService apiService;

    public MeasurementRepository() {
        if (BuildConfig.USE_MOCK_API) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }
    }

    public void getAllMeasurements(Callback<List<MeasurementModel>> callback) {
        Call<List<MeasurementModel>> call = apiService.getUserMeasurement("mock_token1");
        call.enqueue(callback);
    }

    public void getRecommendedMeasurement(Callback<List<RecommendedMeasurementsModel>> callback) {
        Call<List<RecommendedMeasurementsModel>> call = apiService.getRecommendedMeasurements("mock");
        call.enqueue(callback);
    }

    public void updateRecommendedMeasurements(RecommendedMeasurementsModel recommendedMeasurementsModel, Callback<RecommendedMeasurementsModel> callback) {
        Call<RecommendedMeasurementsModel> call = apiService.updateRecommendedMeasurements("mock", recommendedMeasurementsModel);
        call.enqueue(callback);
    }


}
