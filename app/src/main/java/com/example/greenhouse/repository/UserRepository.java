package com.example.greenhouse.repository;

import com.example.greenhouse.BuildConfig;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.UserModel;


import retrofit2.Call;
import retrofit2.Callback;

public class UserRepository {
    private ApiService apiService;

    public UserRepository() {
        if (BuildConfig.USE_MOCK_API) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }
    }

    public void updateUserProfile(UserModel userModel, Callback<UserModel> callback) {
        Call<UserModel> call = apiService.updateUserProfile("mock", userModel);
        call.enqueue(callback);
    }


}
