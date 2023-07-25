package com.example.greenhouse.api;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.MeasurementModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealApiService implements ApiService {
    private ApiService apiService;

    public RealApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return apiService.login(loginRequest);
    }

    @Override
    public Call<List<GreenHouseModel>> getUserGreenHouses(String token) {
        return null;
    }

    @Override
    public Call<GreenHouseModel> createGreenHouse(GreenHouseModel greenhouseModel) {
        return null;
    }

    @Override
    public Call<Void> deleteGreenHouse(int greenhouseId) {
        return null;
    }

    @Override
    public Call<List<MeasurementModel>> getUserMeasurement(String token) {
        return null;
    }
}
