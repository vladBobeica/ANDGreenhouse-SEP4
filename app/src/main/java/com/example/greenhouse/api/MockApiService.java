package com.example.greenhouse.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.mock.MockUserData;
import com.example.greenhouse.utils.RetrofitUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MockApiService implements ApiService {
    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Retrieve the mock user data
        List<MockUserData.User> users = MockUserData.getUsers();

        // Check if the provided email and password match any user in the mock data
        for (MockUserData.User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                // Authentication successful, return a mock LoginResponse with a token
                String token = generateMockToken(); // Generate a mock token
                LoginResponse loginResponse = new LoginResponse(token);
                Log.d(TAG, "Mock user found");

                return RetrofitUtils.createSuccessCall(loginResponse);
            }
        }

        // Authentication failed, return an error response
        Log.d(TAG, "Mock user was not found");
        return RetrofitUtils.createErrorCall(401, new IOException("Invalid credentials"));
    }

    // Utility method to generate a mock token (example implementation)
    private String generateMockToken() {
        return "mock_token";
    }
}
