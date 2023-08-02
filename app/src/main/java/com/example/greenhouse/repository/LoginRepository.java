package com.example.greenhouse.repository;

import com.example.greenhouse.BuildConfig;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private ApiService apiService;

    public LoginRepository() {
        if (BuildConfig.USE_MOCK_API) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }
    }

    public void login(LoginRequest loginRequest, final LoginCallback callback) {
        Call<LoginResponse> call = apiService.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    callback.onLoginSuccess(token);
                } else {
                    callback.onLoginFailure("Login failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onLoginFailure("Login request failed");
            }
        });
    }

    public interface LoginCallback {
        void onLoginSuccess(String token);
        void onLoginFailure(String error);
    }



}
