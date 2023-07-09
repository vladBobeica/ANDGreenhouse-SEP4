package com.example.greenhouse.api;

import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.RegisterRequest;
import com.example.greenhouse.model.RegisterResponse;

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
    public Call<RegisterResponse> register(RegisterRequest registerRequest) {
        return apiService.register(registerRequest);
    }
}
