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
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.repository.MeasurementRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinMaxValuesGreenhouse extends DialogFragment {

    private EditText tempMin;
    private EditText tempMax;
    private EditText humidMin;
    private EditText humidMax;
    private EditText lightMin;
    private EditText lightMax;
    private Button saveChanges;

    private Button applyRecommended;
    private int selectedGreenhouseId;
    private MeasurementRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_minmax_greenhouse, container, false);
        Log.d("MinMaxValues", "onCreateView: Fragment created");

        tempMin = rootView.findViewById(R.id.temperatureMinEditText);
        tempMax = rootView.findViewById(R.id.temperatureMaxEditText);
        humidMin = rootView.findViewById(R.id.humidityMinEditText);
        humidMax = rootView.findViewById(R.id.humidityMaxEditText);
        lightMin = rootView.findViewById(R.id.lightMinEditText);
        lightMax = rootView.findViewById(R.id.lightMaxEditText);
        applyRecommended = rootView.findViewById(R.id.applyRecommendedSettingsButtonComp);
        saveChanges = rootView.findViewById(R.id.saveMinMaxChanges);

        Bundle args = getArguments();
        if (args != null && args.containsKey("selected_greenhouse_id")) {
            selectedGreenhouseId = args.getInt("selected_greenhouse_id");
            Log.d("MinMaxValues", "Selected greenhouse ID: " + selectedGreenhouseId);
            repository = new MeasurementRepository();

            repository.getRecommendedMeasurement(new Callback<List<RecommendedMeasurementsModel>>() {
                @Override
                public void onResponse(Call<List<RecommendedMeasurementsModel>> call, Response<List<RecommendedMeasurementsModel>> response) {
                    if (response.isSuccessful()) {
                        List<RecommendedMeasurementsModel> recommendedMeasurementsList = response.body();

                        Log.d("GreenHouse", "Recommended measurements list size: " + recommendedMeasurementsList.size());

                        for (RecommendedMeasurementsModel recommendedMeasurements : recommendedMeasurementsList) {
                            Log.d("GreenHouse", "Recommended measurements ID: " + recommendedMeasurements.getId());
                            Log.d("MinMaxValues", "selectedGreenhouseId: " + selectedGreenhouseId);
                            if (recommendedMeasurements.getId() == selectedGreenhouseId) {
                                Log.d("MinMaxValues", "Populating EditText fields with recommended measurements");
                                tempMin.setText(String.valueOf(recommendedMeasurements.getMinTemperature()));
                                tempMax.setText(String.valueOf(recommendedMeasurements.getMaxTemperature()));
                                humidMin.setText(String.valueOf(recommendedMeasurements.getMinHumidity()));
                                humidMax.setText(String.valueOf(recommendedMeasurements.getMaxHumidity()));
                                lightMin.setText(String.valueOf(recommendedMeasurements.getMinLight()));
                                lightMax.setText(String.valueOf(recommendedMeasurements.getMaxLight()));
                                Log.d("MinMaxValues", "Populated EditText fields with recommended measurements");
                                break;
                            } else {
                                Log.d("MinMaxValues", "ID did not match");
                            }
                        }
                    } else {
                        Log.e("GreenHouse", "Failed to get recommended measurements. Error code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<RecommendedMeasurementsModel>> call, Throwable t) {
                    Log.e("GreenHouse", "Failed to get recommended measurements. Error: " + t.getMessage());
                }
            });

        }

        applyRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GreenHouse", "Apply button tapped");
                applyRecommendedSettings();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GreenHouse", "Save Changes button tapped");
                saveChangesSettings();

            }
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

    private void applyRecommendedSettings() {

        tempMin.setText("15");
        tempMax.setText("25");

        humidMin.setText("40");
        humidMax.setText("60");

        lightMin.setText("1000");
        lightMax.setText("5000");
    }

    private void saveChangesSettings() {
        String tempMinValue = tempMin.getText().toString();
        String tempMaxValue = tempMax.getText().toString();
        String humidMinValue = humidMin.getText().toString();
        String humidMaxValue = humidMax.getText().toString();
        String lightMinValue = lightMin.getText().toString();
        String lightMaxValue = lightMax.getText().toString();

        RecommendedMeasurementsModel updatedMeasurements = new RecommendedMeasurementsModel(
                selectedGreenhouseId,
                tempMinValue,
                tempMaxValue,
                humidMinValue,
                humidMaxValue,
                lightMinValue,
                lightMaxValue
        );

        repository.updateRecommendedMeasurements(updatedMeasurements, new Callback<RecommendedMeasurementsModel>() {
            @Override
            public void onResponse(Call<RecommendedMeasurementsModel> call, Response<RecommendedMeasurementsModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Recommended measurements updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("GreenHouse", "Failed to update recommended measurements. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RecommendedMeasurementsModel> call, Throwable t) {
                Log.e("GreenHouse", "Failed to update recommended measurements. Error: " + t.getMessage());
            }
        });
    }

}

