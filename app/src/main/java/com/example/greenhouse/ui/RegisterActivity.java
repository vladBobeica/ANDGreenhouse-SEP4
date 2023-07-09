package com.example.greenhouse.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.greenhouse.R;
import com.example.greenhouse.api.ApiService;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.api.RealApiService;
import com.example.greenhouse.model.LoginResponse;
import com.example.greenhouse.model.RegisterRequest;
import com.example.greenhouse.model.RegisterResponse;
import com.example.greenhouse.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameField, emailField, passwordField;
    private Button registerButton;
    private ImageView goBackToIntroduction;

    private ApiService apiService;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        goBackToIntroduction = findViewById(R.id.goBackButtonRegister);
        nameField = findViewById(R.id.nameFieldRegister);
        emailField = findViewById(R.id.emailFieldRegister);
        passwordField = findViewById(R.id.passwordFieldRegister);
        registerButton = findViewById(R.id.registerButton);

        boolean useMockApi = true;

        if (useMockApi) {
            apiService = new MockApiService();
        } else {
            apiService = new RealApiService();
        }

        userRepository = new UserRepository(apiService);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                RegisterRequest registerRequest = new RegisterRequest(name, email, password);


                Call<RegisterResponse> call = userRepository.register(registerRequest);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            RegisterResponse registerResponse = response.body();
                            String token = registerResponse.getToken();
                            Toast.makeText(RegisterActivity.this, "Created account.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterActivity.this, "Big FAIL", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Login request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        goBackToIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
