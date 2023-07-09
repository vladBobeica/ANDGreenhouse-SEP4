package com.example.greenhouse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenhouse.MainActivity;
import com.example.greenhouse.R;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private ApiService apiService;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.emailEdit);
        editTextPassword = findViewById(R.id.passwordEdit);
        buttonLogin = findViewById(R.id.LoginButton);

        boolean useMockApi = true;

        if (useMockApi) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }

        userRepository = new UserRepository(apiService);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                LoginRequest loginRequest = new LoginRequest(email, password);
                Call<LoginResponse> call = userRepository.login(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            String token = loginResponse.getToken();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Finish LoginActivity to prevent going back
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Login request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
