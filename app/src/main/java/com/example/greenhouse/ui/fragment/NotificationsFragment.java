package com.example.greenhouse.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.databinding.FragmentNotificationsBinding;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.NotificationModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.ui.adapter.NotificationsAdapter;
import com.example.greenhouse.ui.viewmodel.NotificationsViewModel;
import com.example.greenhouse.utils.AlertManager;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;
    private boolean alertsSimulated = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView notificationsRV = root.findViewById(R.id.notificationsRV);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        notificationsRV.setLayoutManager(layoutManager);

        NotificationsAdapter notificationsAdapter = new NotificationsAdapter(new ArrayList<>());
        notificationsRV.setAdapter(notificationsAdapter);

        notificationsViewModel.getNotifications().observe(getViewLifecycleOwner(), notifications -> {
            notificationsAdapter.setNotifications(notifications);
            notificationsAdapter.notifyDataSetChanged();
        });

        AlertManager alertManager = new AlertManager(requireContext());

        String greenhouseName = "Example Greenhouse";
        String exceededParameter = "Temperature";
        String message = "Temperature is too low!";
        NotificationModel notification = new NotificationModel(alertManager.generateNotificationId(), greenhouseName, exceededParameter, message);
        notificationsViewModel.addNotification(notification);

        if (!alertsSimulated) {
            MeasurementModel liveMeasurements = new MeasurementModel(1, "2023-08-04", "20", "40", "800");

            RecommendedMeasurementsModel recommendedValues = new RecommendedMeasurementsModel(
                    "22°C", "26°C", "40%", "60%", "800 Lux", "1200 Lux"
            );

            alertManager.checkAndSendAlerts(liveMeasurements, recommendedValues);

            alertsSimulated = true;
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
