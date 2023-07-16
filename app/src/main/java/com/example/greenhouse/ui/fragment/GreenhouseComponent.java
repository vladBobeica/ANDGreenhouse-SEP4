package com.example.greenhouse.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.ui.adapter.MeasurementsAdapter;
import com.example.greenhouse.databinding.FragmentGreenhouseComponentBinding;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.ui.viewmodel.GreenhouseComponentViewModel;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseComponent extends Fragment {

    private FragmentGreenhouseComponentBinding binding;
    private MeasurementsAdapter adapter;
    private GreenhouseComponentViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGreenhouseComponentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel = new ViewModelProvider(this).get(GreenhouseComponentViewModel.class);
        viewModel.getMeasurementList().observe(getViewLifecycleOwner(), measurements -> {
            adapter = new MeasurementsAdapter(measurements);
            recyclerView.setAdapter(adapter);
        });

        viewModel.addSampleData(); // Call this method to add sample data (moved to ViewModel)

        ImageView goBackButton = binding.goBackToDashboard;
        goBackButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

