package com.example.greenhouse.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.greenhouse.R;

public class IntroductionActivity extends AppCompatActivity {
    private Button buttonToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        buttonToLogin = findViewById(R.id.buttonToLogin);

        buttonToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(IntroductionActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
