package com.example.greenhouse.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.databinding.FragmentNotificationsBinding;
import com.example.greenhouse.ui.adapter.NotificationsAdapter;
import com.example.greenhouse.ui.viewmodel.NotificationsViewModel;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

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

        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}