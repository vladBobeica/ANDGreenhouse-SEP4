package com.example.greenhouse.api;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("greenHouses")
    Call<List<GreenHouseModel>> getUserGreenHouses(@Header("Authorization") String token);

    @GET("greenHouses")
    Call<GreenHouseModel> getUserGreenhouseById(@Header("Authorization") String token, int id);

    @POST("greenHouses")
    Call<GreenHouseModel> createGreenHouse(@Body GreenHouseModel greenhouseModel);

    @DELETE("greenHouses/{id}")
    Call<Void> deleteGreenHouse(@Path("id") int greenhouseId);

    @GET("measurement")
    Call<List<MeasurementModel>> getUserMeasurement(@Header("Authorization") String token);

    @GET("recommendedMeasurement")
    Call<List<RecommendedMeasurementsModel>> getRecommendedMeasurements(@Header("Authorization") String token);

    @POST("recommendedMeasurement")
    Call<RecommendedMeasurementsModel> updateRecommendedMeasurements(@Header("Authorization") String token, @Body RecommendedMeasurementsModel recommendedMeasurementsModel);

    @POST("user")
    Call<UserModel> updateUserProfile(@Header("Authorization") String token, @Body UserModel userModel);
}
