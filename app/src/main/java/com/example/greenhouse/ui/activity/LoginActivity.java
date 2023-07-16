package com.example.greenhouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenhouse.MainActivity;
import com.example.greenhouse.R;
import com.example.greenhouse.model.LoginRequest;
import com.example.greenhouse.repository.LoginRepository;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private LoginRepository loginRepository;
    private ImageView goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.emailEdit);
        editTextPassword = findViewById(R.id.passwordEdit);
        buttonLogin = findViewById(R.id.loginButton);
        goBackButton = findViewById(R.id.goBackButtonLogin);
        loginRepository = new LoginRepository();

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            LoginRequest loginRequest = new LoginRequest(email, password);

            loginRepository.login(loginRequest, new LoginRepository.LoginCallback() {
                @Override
                public void onLoginSuccess(String token) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onLoginFailure(String error) {
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
