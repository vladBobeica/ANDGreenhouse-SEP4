package com.example.greenhouse.repository;

import com.example.greenhouse.BuildConfig;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.GreenHouseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GreenHouseRepository {
    private ApiService apiService;

    public GreenHouseRepository() {
        if (BuildConfig.USE_MOCK_API) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }
    }

    public void getAllGreenHouses(Callback<List<GreenHouseModel>> callback) {
        Call<List<GreenHouseModel>> call = apiService.getUserGreenHouses("mock_token");
        call.enqueue(callback);
    }

    public void createGreenHouse(GreenHouseModel greenhouseModel, Callback<GreenHouseModel> callback) {
        Call<GreenHouseModel> call = apiService.createGreenHouse(greenhouseModel);
        call.enqueue(callback);
    }

    public void removeGreenHouse(int greenhouseId, Callback<Void> callback) {
        Call<Void> call = apiService.deleteGreenHouse(greenhouseId);
        call.enqueue(callback);
    }

}
