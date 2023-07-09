package com.example.greenhouse.repository;


import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.RegisterRequest;
import com.example.greenhouse.model.RegisterResponse;

import retrofit2.Call;

public class UserRepository {
    private ApiService apiService;

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return apiService.login(loginRequest);
    }

    public Call<RegisterResponse> register(RegisterRequest registerRequest) {
        return apiService.register(registerRequest);
    }
}