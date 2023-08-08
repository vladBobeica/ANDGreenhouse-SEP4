package com.example.greenhouse.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.MeasurementsAdapter;
import com.example.greenhouse.databinding.FragmentGreenhouseComponentBinding;
import com.example.greenhouse.ui.modal.MinMaxValuesGreenhouse;
import com.example.greenhouse.viewmodel.GreenhouseComponentViewModel;

import java.util.List;

public class GreenhouseComponent extends Fragment {

    private FragmentGreenhouseComponentBinding binding;
    private MeasurementsAdapter adapter;
    private GreenhouseComponentViewModel viewModel;

    private GreenHouseRepository repository;
    private int selectedGreenhouseId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGreenhouseComponentBinding.inflate(inflater, container, false);

        repository = new GreenHouseRepository();

        Bundle args = getArguments();
        if (args != null && args.containsKey("greenhouse_id")) {
            selectedGreenhouseId = args.getInt("greenhouse_id");
            Log.d("GreenhouseComponent", "Selected greenhouse ID: " + selectedGreenhouseId);
        }

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel = new ViewModelProvider(this).get(GreenhouseComponentViewModel.class);
        viewModel.getMeasurementList().observe(getViewLifecycleOwner(), this::updateUI);

        viewModel.fetchMeasurements();

        ImageView goBackButton = binding.goBackToDashboard;
        goBackButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard);
        });

        ImageView greenhouseSettings = binding.settingsMeasurements;
        greenhouseSettings.setOnClickListener(view -> {
            MinMaxValuesGreenhouse dialogFragment = new MinMaxValuesGreenhouse();

            Bundle bundle = new Bundle();
            bundle.putInt("selected_greenhouse_id", selectedGreenhouseId);
            dialogFragment.setArguments(bundle);

            dialogFragment.show(getChildFragmentManager(), "MinMaxValuesDialog");
        });

        return binding.getRoot();
    }

    private void updateUI(List<MeasurementModel> measurements) {
        adapter = new MeasurementsAdapter(measurements);
        binding.recyclerView.setAdapter(adapter);

        if (!measurements.isEmpty()) {
            MeasurementModel latestMeasurement = measurements.get(0);

            TextView temperatureTextView = binding.textTemperatureValue;
            TextView humidityTextView = binding.textHumidityValue;
            TextView lightTextView = binding.textLightValue;

            temperatureTextView.setText(latestMeasurement.getTemperature());
            humidityTextView.setText(latestMeasurement.getHumidity());
            lightTextView.setText(latestMeasurement.getLight());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
