package com.example.greenhouse.ui.components;

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
import com.example.greenhouse.adapter.MeasurementsAdapter;
import com.example.greenhouse.databinding.FragmentGreenhouseComponentBinding;
import com.example.greenhouse.model.MeasurementModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseComponent extends Fragment {

    private FragmentGreenhouseComponentBinding binding;
    private List<MeasurementModel> measurementList;
    private MeasurementsAdapter adapter;

    private ImageView goBackButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGreenhouseComponentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        measurementList = new ArrayList<>();
        adapter = new MeasurementsAdapter(measurementList);
        recyclerView.setAdapter(adapter);


        addSampleData();

        goBackButton = root.findViewById(R.id.goBackToDashboard);
        goBackButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addSampleData() {
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-13", "23°C", "70%", "800 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));

        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        measurementList.add(new MeasurementModel("2023-07-14", "25°C", "65%", "1000 Lux"));
        adapter.notifyDataSetChanged();
    }
}
