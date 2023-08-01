package com.example.greenhouse.utils;


import android.widget.EditText;

public class RecommendationUtils {

    public static void applyRecommendedSettings(EditText temperatureMinEditText, EditText temperatureMaxEditText,
                                                EditText humidityMinEditText, EditText humidityMaxEditText,
                                                EditText lightMinEditText, EditText lightMaxEditText) {
        temperatureMinEditText.setText("15");
        temperatureMaxEditText.setText("25");

        humidityMinEditText.setText("40");
        humidityMaxEditText.setText("60");

        lightMinEditText.setText("1000");
        lightMaxEditText.setText("5000");
    }
}
