package com.example.greenhouse.api;

import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockApiService implements ApiService {

    private List<UserAccount> userAccounts;

    public MockApiService() {
        // Create a list of user accounts
        userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("one@gmail.com", "111"));
        userAccounts.add(new UserAccount("vlad@gmail.com", "1111"));
        // Add more user accounts as needed
    }

    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        // Extract the email and password from the login request
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Check if the provided credentials match any user account
        for (UserAccount account : userAccounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                // Create a mock response for successful login
                LoginResponse mockResponse = new LoginResponse();
                mockResponse.setToken("mock_token");

                // Create a dummy Call object and immediately execute the callback
                Call<LoginResponse> call = new Call<LoginResponse>() {
                    @Override
                    public Response<LoginResponse> execute() {
                        return null; // Not used in this case
                    }

                    @Override
                    public void enqueue(Callback<LoginResponse> callback) {
                        // Simulate a successful response
                        Response<LoginResponse> response = Response.success(mockResponse);
                        callback.onResponse(this, response);
                    }

                    @Override
                    public boolean isExecuted() {
                        return false;
                    }

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public boolean isCanceled() {
                        return false;
                    }

                    @Override
                    public Call<LoginResponse> clone() {
                        return null;
                    }

                    @Override
                    public Request request() {
                        return null;
                    }

                    @Override
                    public Timeout timeout() {
                        return null;
                    }

                    // Rest of the methods...

                    // ...
                };

                return call;
            }
        }

        // If no matching user account found, simulate a failed response
        Call<LoginResponse> call = new Call<LoginResponse>() {
            @Override
            public Response<LoginResponse> execute() {
                return null; // Not used in this case
            }

            @Override
            public void enqueue(Callback<LoginResponse> callback) {
                // Simulate a failed response
                Response<LoginResponse> response = Response.error(401, ResponseBody.create(null, ""));
                callback.onResponse(this, response);
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<LoginResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }

            @Override
            public Timeout timeout() {
                return null;
            }

            // Rest of the methods...

            // ...
        };

        return call;
    }

    // Inner class representing a user account
    private static class UserAccount {
        private String email;
        private String password;

        public UserAccount(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
