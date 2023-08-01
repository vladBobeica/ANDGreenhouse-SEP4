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
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.fragment.DashboardFragment;
import com.example.greenhouse.utils.RecommendationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button addNewGreenhouse;

    private static final String TAG = AddGreenhouseModal.class.getSimpleName();
    private GreenHouseRepository repository;

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
        addNewGreenhouse = rootView.findViewById(R.id.addGreenhouseButton);
        repository = new GreenHouseRepository();
        applyRecommendedSettingsButton.setOnClickListener(v -> {
            RecommendationUtils.applyRecommendedSettings(temperatureMinEditText, temperatureMaxEditText,
                    humidityMinEditText, humidityMaxEditText, lightMinEditText, lightMaxEditText);
        });

        addNewGreenhouse.setOnClickListener(view -> {
            String name = greenHouseName.getText().toString().trim();
            String address = greenHouseAddress.getText().toString().trim();

            String temperatureMin = temperatureMinEditText.getText().toString().trim();
            String temperatureMax = temperatureMaxEditText.getText().toString().trim();

            String humidityMin = humidityMinEditText.getText().toString().trim();
            String humidityMax = humidityMaxEditText.getText().toString().trim();

            String lightMin = lightMinEditText.getText().toString().trim();
            String lightMax = lightMaxEditText.getText().toString().trim();

            if (!name.isEmpty() && !address.isEmpty() && !temperatureMin.isEmpty() && !temperatureMax.isEmpty() &&
                    !humidityMin.isEmpty() && !humidityMax.isEmpty() && !lightMin.isEmpty() && !lightMax.isEmpty()) {
                addNewGreenHouse(name, address, new RecommendedMeasurementsModel(temperatureMin, temperatureMax,
                        humidityMin, humidityMax, lightMin, lightMax));
            } else {
                Toast.makeText(requireContext(), "Please, provide values for all the fields.", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_add_greenhouse);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }

    private void addNewGreenHouse(String name, String address, RecommendedMeasurementsModel recommendedMeasurementsModel) {
        GreenHouseModel newGreenhouse = new GreenHouseModel(name, address, recommendedMeasurementsModel);

        repository.createGreenHouse(newGreenhouse, new Callback<GreenHouseModel>() {
            @Override
            public void onResponse(Call<GreenHouseModel> call, Response<GreenHouseModel> response) {
                if (response.isSuccessful()) {
                    GreenHouseModel createdGreenhouse = response.body();
                    if (createdGreenhouse != null) {
                        DashboardFragment dashboardFragment = (DashboardFragment) getParentFragment();
                        if (dashboardFragment != null) {
                            dashboardFragment.addGreenhouseToList(createdGreenhouse);
                        }
                        dismiss();
                    }
                } else {
                    Log.e(TAG, "Failed to create greenhouse. Error code: " + response.code());
                    Toast.makeText(requireContext(), "Failed to add the greenhouse.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GreenHouseModel> call, Throwable t) {
                Log.e(TAG, "Failed to create greenhouse. Error: " + t.getMessage());
                Toast.makeText(requireContext(), "Failed to add the greenhouse.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
