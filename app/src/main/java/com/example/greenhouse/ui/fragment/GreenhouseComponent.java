package com.example.greenhouse.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.MeasurementsAdapter;
import com.example.greenhouse.databinding.FragmentGreenhouseComponentBinding;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.ui.modal.MinMaxValuesGreenhouse;
import com.example.greenhouse.ui.viewmodel.GreenhouseComponentViewModel;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGreenhouseComponentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repository = new GreenHouseRepository();

        Bundle args = getArguments();

        if (args != null && args.containsKey("greenhouse_id")) {
            int selectedGreenhouseId = args.getInt("greenhouse_id");
            repository.getGreenhouseById(selectedGreenhouseId, new Callback<GreenHouseModel>() {
                @Override
                public void onResponse(Call<GreenHouseModel> call, Response<GreenHouseModel> response) {
                    if (response.isSuccessful()) {
                        greenHouseModel = response.body();
                        // Update your UI to display the details
                    } else {
                        // Handle unsuccessful response
                    }
                }

                @Override
                public void onFailure(Call<GreenHouseModel> call, Throwable t) {
                    // Handle failure (e.g., network error)
                }
            });
        }

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel = new ViewModelProvider(this).get(GreenhouseComponentViewModel.class);
        viewModel.getMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            adapter = new MeasurementsAdapter(measurements);
            recyclerView.setAdapter(adapter);
        });

        // Move the addSampleData call after initializing the viewModel
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
                dialogFragment.show(getChildFragmentManager(), "MinMaxValuesDialog");
            }
        });
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

