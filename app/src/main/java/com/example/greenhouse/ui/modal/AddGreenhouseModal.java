package com.example.greenhouse.ui.modal;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.greenhouse.R;

public class AddGreenhouseModal extends DialogFragment {

    private EditText greenHouseName;
    private EditText greenHouseAddress;

    private EditText temperatureMinEditText;
    private EditText temperatureMaxEditText;

    private EditText humidityMinEditText;
    private EditText humidityMaxEditText;

    private EditText lightMinEditText;
    private EditText lightMaxEditText;

    private Button applyRecommendedSettingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_add_greenhouse, container, false);

        greenHouseName = rootView.findViewById(R.id.greenhouseNameEditText);
        greenHouseAddress = rootView.findViewById(R.id.greenhouseAddressEditText);

        temperatureMinEditText = rootView.findViewById(R.id.temperatureMinEditText);
        temperatureMaxEditText = rootView.findViewById(R.id.temperatureMaxEditText);

        humidityMinEditText = rootView.findViewById(R.id.humidityMinEditText);
        humidityMaxEditText = rootView.findViewById(R.id.humidityMaxEditText);

        lightMinEditText = rootView.findViewById(R.id.lightMinEditText);
        lightMaxEditText = rootView.findViewById(R.id.lightMaxEditText);

        applyRecommendedSettingsButton = rootView.findViewById(R.id.applyRecommendedSettingsButton);

        applyRecommendedSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GreenHouse", "Apply button tapped");
                applyRecommendedSettings();
            }
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_add_greenhouse);

        applyRecommendedSettingsButton = dialog.findViewById(R.id.applyRecommendedSettingsButton);

        applyRecommendedSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GreenHouse", "Apply button tapped");
                applyRecommendedSettings();
            }
        });

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }

    private void applyRecommendedSettings() {
        // Set predefined values for min and max fields
        temperatureMinEditText.setText("15");
        temperatureMaxEditText.setText("25");

        humidityMinEditText.setText("40");
        humidityMaxEditText.setText("60");

        lightMinEditText.setText("1000");
        lightMaxEditText.setText("5000");
    }

}
