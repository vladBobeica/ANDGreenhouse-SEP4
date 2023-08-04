package com.example.greenhouse.ui.fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.MeasurementsAdapter;
import com.example.greenhouse.databinding.FragmentGreenhouseComponentBinding;
import com.example.greenhouse.ui.modal.MinMaxValuesGreenhouse;
import com.example.greenhouse.ui.viewmodel.GreenhouseComponentViewModel;
import com.example.greenhouse.utils.AlertManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenhouseComponent extends Fragment {

    private FragmentGreenhouseComponentBinding binding;
    private MeasurementsAdapter adapter;
    private GreenhouseComponentViewModel viewModel;

    private GreenHouseRepository repository;
    private GreenHouseModel greenHouseModel;

    private ImageView greenhouseSettings;
    private int selectedGreenhouseId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGreenhouseComponentBinding.inflate(inflater, container, false);

        repository = new GreenHouseRepository();

        Bundle args = getArguments();

        if (args != null && args.containsKey("greenhouse_id")) {
            selectedGreenhouseId = args.getInt("greenhouse_id");
            Log.d("GreenhouseComponent", "Selected greenhouse ID: " + selectedGreenhouseId);
            repository.getGreenhouseById(selectedGreenhouseId, new Callback<GreenHouseModel>() {
                @Override
                public void onResponse(Call<GreenHouseModel> call, Response<GreenHouseModel> response) {
                    if (response.isSuccessful()) {
                        greenHouseModel = response.body();
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<GreenHouseModel> call, Throwable t) {

                }
            });
        }

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel = new ViewModelProvider(this).get(GreenhouseComponentViewModel.class);
        viewModel.getMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            adapter = new MeasurementsAdapter(measurements);
            recyclerView.setAdapter(adapter);

            if (!measurements.isEmpty()) {
                MeasurementModel latestMeasurement = measurements.get(0);

                TextView temperatureTextView = binding.textTemperatureValue;
                TextView humidityTextView = binding.textHumidityValue;
                TextView lightTextView = binding.textLightValue;

                temperatureTextView.setText(latestMeasurement.getTemperature());
                humidityTextView.setText(latestMeasurement.getHumidity());
                lightTextView.setText(latestMeasurement.getLight());
            }
        });

        viewModel.addSampleData();

        ImageView goBackButton = binding.goBackToDashboard;
        goBackButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard);
        });

        greenhouseSettings = binding.settingsMeasurements;
        greenhouseSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MinMaxValuesGreenhouse dialogFragment = new MinMaxValuesGreenhouse();

                Bundle bundle = new Bundle();
                bundle.putInt("selected_greenhouse_id", selectedGreenhouseId);
                dialogFragment.setArguments(bundle);

                dialogFragment.show(getChildFragmentManager(), "MinMaxValuesDialog");
            }
        });

        viewModel.getMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            adapter = new MeasurementsAdapter(measurements);
            recyclerView.setAdapter(adapter);

            AlertManager alertManager = new AlertManager(requireContext());
            RecommendedMeasurementsModel recommendedValues = greenHouseModel.getRecommendedMeasurementsModel();

            for (MeasurementModel measurement : measurements) {
                alertManager.checkAndSendAlerts(measurement, recommendedValues);
            }
        });


        checkPermissionAndHandle();

        return binding.getRoot();
    }


    private void checkPermissionAndHandle() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) {
            viewModel.addSampleData();
        } else {
            requestInternetPermission();
        }
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{android.Manifest.permission.INTERNET},
                AlertManager.INTERNET_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == AlertManager.INTERNET_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.addSampleData();
            } else {

                Toast.makeText(requireContext(), "Permission denied. Cannot show alerts.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
