package com.example.greenhouse.api;

import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.RegisterRequest;
import com.example.greenhouse.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

}
