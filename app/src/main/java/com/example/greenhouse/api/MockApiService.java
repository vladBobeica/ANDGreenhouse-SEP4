package com.example.greenhouse.api;

import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.RegisterRequest;
import com.example.greenhouse.model.RegisterResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockApiService implements ApiService {

    private Map<String, UserAccount> userAccounts;
    private List<String> registeredEmails;

    public MockApiService() {
        userAccounts = new HashMap<>();
        registeredEmails = new ArrayList<>();

        userAccounts.put("one@gmail.com", new UserAccount("one@gmail.com", "111"));
        userAccounts.put("vlad@gmail.com", new UserAccount("vlad@gmail.com", "1111"));
        userAccounts.put("alin@gmail.com", new UserAccount("alin@gmail.com", "123"));
    }

    @Override
    public Call<LoginResponse> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserAccount userAccount = userAccounts.get(email);

        if (userAccount != null && userAccount.getPassword().equals(password)) {
            LoginResponse mockResponse = new LoginResponse();
            mockResponse.setToken("mock_token");

            Response<LoginResponse> response = Response.success(mockResponse);
            return CallResult.successCall(response);
        } else {
            Response<LoginResponse> response = Response.error(401, ResponseBody.create(null, ""));
            return CallResult.errorCall(response);
        }
    }

    @Override
    public Call<RegisterResponse> register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        if (registeredEmails.contains(email)) {
            RegisterResponse failedResponse = new RegisterResponse();
            failedResponse.setToken(null);

            Response<RegisterResponse> response = Response.error(401, ResponseBody.create(null, ""));
            return CallResult.errorCall(response);
        } else {
            registeredEmails.add(email);
            userAccounts.put(email, new UserAccount(email, password));

            RegisterResponse successResponse = new RegisterResponse();
            successResponse.setToken("mock_token");

            Response<RegisterResponse> response = Response.success(successResponse);
            return CallResult.successCall(response);
        }
    }

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

    private static class CallResult<T> implements Call<T> {
        private final Response<T> response;

        private CallResult(Response<T> response) {
            this.response = response;
        }

        public static <T> Call<T> successCall(Response<T> response) {
            return new CallResult<>(response);
        }

        public static <T> Call<T> errorCall(Response<T> response) {
            return new CallResult<>(response);
        }

        @Override
        public Response<T> execute() {
            return response;
        }

        @Override
        public void enqueue(Callback<T> callback) {
            callback.onResponse(this, response);
        }

        @Override
        public boolean isExecuted() {
            return false;
        }
        @Override
        public Call<T> clone() {
            return null;
        }
        @Override
        public void cancel() {
        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Request request() {
            return null;
        }

        @Override
        public Timeout timeout() {
            return null;
        }
    }
}
