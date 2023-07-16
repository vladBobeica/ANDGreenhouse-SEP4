package com.example.greenhouse.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.adapter.OptionsAdapter;
import com.example.greenhouse.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private RecyclerView settingsRecyclerView;
    private OptionsAdapter optionsAdapter;
    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsRecyclerView = rootView.findViewById(R.id.settingsRV);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> optionsList = new ArrayList<>();
        optionsList.add("Add users");
        optionsList.add("View users list");
        optionsList.add("Edit profile");
        optionsList.add("Sign out");

        optionsAdapter = new OptionsAdapter(optionsList);
        settingsRecyclerView.setAdapter(optionsAdapter);

        return rootView;
    }
}
