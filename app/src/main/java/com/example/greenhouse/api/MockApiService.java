package com.example.greenhouse.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.UserModel;
import com.example.greenhouse.repository.mock.MockGreenHouseRepository;
import com.example.greenhouse.repository.mock.MockMeasurementRepository;
import com.example.greenhouse.repository.mock.MockUserRepository;
import com.example.greenhouse.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class MockApiService implements ApiService {
    private static String mockToken;

    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Retrieve the mock user data
        List<UserModel> users = MockUserRepository.getUsers();

        // Check if the provided email and password match any user in the mock data
        for (UserModel user : users) {
            if (user.getEmail().equals(email) && password.equals("111")) {
                // Authentication successful, return a mock LoginResponse with a token
                String token = generateMockToken(); // Generate a mock token
                LoginResponse loginResponse = new LoginResponse(token);
                Log.d(TAG, "Mock user found");

                // Store token in mock
                mockToken = String.valueOf(user.getId());

                return RetrofitUtils.createSuccessCall(loginResponse);
            }
        }

        // Authentication failed, return an error response
        Log.d(TAG, "Mock user was not found");
        return RetrofitUtils.createErrorCall(401, new IOException("Invalid credentials"));
    }

    @Override
    public Call<List<GreenHouseModel>> getUserGreenHouses(String token) {
        List<GreenHouseModel> mockGreenHouses = MockGreenHouseRepository.getGreenHouses();

        List<GreenHouseModel> usersGreenHouses = new ArrayList<>();

        // Vlad's green houses
        if (mockToken.equals("1")) {
            usersGreenHouses.addAll(
                    Arrays.asList(
                            mockGreenHouses.get(0),
                            mockGreenHouses.get(3)
                    )
            );
        } else if (mockToken.equals("2")) {
            usersGreenHouses.addAll(
                    Arrays.asList(
                            mockGreenHouses.get(1),
                            mockGreenHouses.get(3)
                    )
            );
        } else if (mockToken.equals("3")) {
            usersGreenHouses.addAll(
                    Arrays.asList(
                            mockGreenHouses.get(2),
                            mockGreenHouses.get(3)
                    )
            );
        } else {
            usersGreenHouses.addAll(
                    Arrays.asList(
                            mockGreenHouses.get(3)
                    )
            );
        }

        return RetrofitUtils.createSuccessCall(usersGreenHouses);
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

    // Utility method to generate a mock token (example implementation)
    public String generateMockToken() {
        return "mock_token";
    }
}
