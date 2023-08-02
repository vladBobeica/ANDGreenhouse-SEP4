package com.example.greenhouse.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.model.UserModel;
import com.example.greenhouse.repository.mock.MockGreenHouseRepository;
import com.example.greenhouse.repository.mock.MockMeasurementRepository;
import com.example.greenhouse.repository.mock.MockRecommendedMeasurements;
import com.example.greenhouse.repository.mock.MockUserRepository;
import com.example.greenhouse.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;

public class MockApiService implements ApiService {
    private static String mockToken;

    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        List<UserModel> users = MockUserRepository.getUsers();


        for (UserModel user : users) {
            if (user.getEmail().equals(email) && password.equals("111")) {

                String token = generateMockToken();
                LoginResponse loginResponse = new LoginResponse(token);
                Log.d(TAG, "Mock user found");

                mockToken = String.valueOf(user.getId());

                return RetrofitUtils.createSuccessCall(loginResponse);
            }
        }


        Log.d(TAG, "Mock user was not found");
        return RetrofitUtils.createErrorCall(401, new IOException("Invalid credentials"));
    }

    @Override
    public Call<List<GreenHouseModel>> getUserGreenHouses(String token) {
        List<GreenHouseModel> mockGreenHouses = MockGreenHouseRepository.getGreenHouses();
        List<GreenHouseModel> usersGreenHouses = new ArrayList<>();


        if ("1".equals(mockToken)) {
            if (mockGreenHouses.size() > 0) {
                usersGreenHouses.add(mockGreenHouses.get(0));
            }
        } else if ("2".equals(mockToken)) {
            if (mockGreenHouses.size() > 1) {
                usersGreenHouses.add(mockGreenHouses.get(1));
            }
        } else if ("3".equals(mockToken)) {
            if (mockGreenHouses.size() > 2) {
                usersGreenHouses.add(mockGreenHouses.get(2));
            }
        }

        if (mockGreenHouses.size() > 3) {
            for (int i = 3; i < mockGreenHouses.size(); i++) {
                usersGreenHouses.add(mockGreenHouses.get(i));
            }
        }

        return RetrofitUtils.createSuccessCall(usersGreenHouses);
    }

    @Override
    public Call<GreenHouseModel> getUserGreenhouseById(String token, int id) {
        Optional<GreenHouseModel> foundGreenhouse = MockGreenHouseRepository.getGreenHouseById(id);

        if (foundGreenhouse.isPresent()) {
            return RetrofitUtils.createSuccessCall(foundGreenhouse.get());
        }

        return RetrofitUtils.createErrorCall(404, new IOException("GreenHouse not found"));
    }


    @Override
    public Call<GreenHouseModel> createGreenHouse(GreenHouseModel greenhouseModel) {
        GreenHouseModel mockGreenHouses = MockGreenHouseRepository.addGreenHouse(greenhouseModel);

        return RetrofitUtils.createSuccessCall(mockGreenHouses);
    }

    @Override
    public Call<Void> deleteGreenHouse(int greenHouseId) {
        boolean wasDeleted = MockGreenHouseRepository.removeGreenHouse(greenHouseId);

        if (wasDeleted) {
            return RetrofitUtils.createSuccessCall();
        }

        Log.d(TAG, "GreenHouse not found");
        return RetrofitUtils.createErrorCall(404, new IOException("GreenHouse not found"));
    }

    @Override
    public Call<List<MeasurementModel>> getUserMeasurement(String token) {
        List<MeasurementModel> mockMeasurement = MockMeasurementRepository.getMeasurement();

        return RetrofitUtils.createSuccessCall(mockMeasurement);
    }

    @Override
    public Call<List<RecommendedMeasurementsModel>> getRecommendedMeasurements(String token) {
        List<RecommendedMeasurementsModel> mockMeasurement = MockRecommendedMeasurements.getRecommendedMeasurements();

        return RetrofitUtils.createSuccessCall(mockMeasurement);
    }

    @Override
    public Call<RecommendedMeasurementsModel> updateRecommendedMeasurements(String token, RecommendedMeasurementsModel recommendedMeasurementsModel) {
        Optional<GreenHouseModel> foundGreenHouseModel = MockGreenHouseRepository.getGreenHouseById(recommendedMeasurementsModel.getId());

        if (foundGreenHouseModel.isPresent()) {
            foundGreenHouseModel.get().setRecommendedMeasurementsModel(recommendedMeasurementsModel);

            return RetrofitUtils.createSuccessCall(recommendedMeasurementsModel);
        }

        return RetrofitUtils.createErrorCall(404, new IOException("GreenHouse not found"));
    }

    public String generateMockToken() {
        return "mock_token";
    }
}
