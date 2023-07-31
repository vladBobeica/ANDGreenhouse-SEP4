package com.example.greenhouse.ui.modal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.greenhouse.R;

public class MinMaxValuesGreenhouse extends DialogFragment {

    private EditText tempMin;
    private EditText tempMax;
    private EditText humidMin;
    private EditText humidMax;
    private EditText lightMin;
    private EditText lightMax;
    private Button saveChanges;

    private CheckBox valuesCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_minmax_greenhouse, container, false);
        tempMin = rootView.findViewById(R.id.temperatureMinEditText);
        tempMax = rootView.findViewById(R.id.temperatureMaxEditText);
        humidMin = rootView.findViewById(R.id.humidityMinEditText);
        humidMax = rootView.findViewById(R.id.humidityMaxEditText);
        lightMin = rootView.findViewById(R.id.lightMinEditText);
        lightMax = rootView.findViewById(R.id.lightMaxEditText);
        valuesCheck = rootView.findViewById(R.id.temperatureKeepRecommendedCheckbox);
        saveChanges = rootView.findViewById(R.id.saveMinMaxChanges);

        // Disable the EditText fields by default
        setEditTextFieldsEnabled(false);

        // Set the recommended settings values (you can replace these with your actual values)
        double recommendedTempMin = 20.0;
        double recommendedTempMax = 30.0;
        tempMin.setText(String.valueOf(recommendedTempMin));
        tempMax.setText(String.valueOf(recommendedTempMax));

        // Set the checkbox listener
        valuesCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setEditTextFieldsEnabled(!isChecked); // Enable or disable EditText fields based on checkbox state
            if (isChecked) {
                // If the checkbox is checked, set the recommended settings values to the EditText fields
                tempMin.setText(String.valueOf(recommendedTempMin));
                tempMax.setText(String.valueOf(recommendedTempMax));
            }
        });

        saveChanges.setOnClickListener(v -> {
            saveMinMaxValues();
            dismiss(); // Close the dialog after saving the values
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_minmax_greenhouse);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }

    // Helper method to enable or disable the EditText fields
    private void setEditTextFieldsEnabled(boolean enabled) {
        tempMin.setEnabled(enabled);
        tempMax.setEnabled(enabled);
        humidMin.setEnabled(enabled);
        humidMax.setEnabled(enabled);
        lightMin.setEnabled(enabled);
        lightMax.setEnabled(enabled);
    }

    // Method to retrieve the input values from the EditText fields when the "Save" button is clicked
    private void saveMinMaxValues() {
        String tempMinValue = tempMin.getText().toString();
        String tempMaxValue = tempMax.getText().toString();
        String humidMinValue = humidMin.getText().toString();
        String humidMaxValue = humidMax.getText().toString();
        String lightMinValue = lightMin.getText().toString();
        String lightMaxValue = lightMax.getText().toString();

        // TODO: Save the values to your desired storage or perform any other actions

        // Show a toast message to indicate that the values are saved
        Toast.makeText(requireContext(), "Values saved", Toast.LENGTH_SHORT).show();
    }
}
