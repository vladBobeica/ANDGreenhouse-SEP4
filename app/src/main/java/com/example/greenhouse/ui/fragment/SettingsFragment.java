package com.example.greenhouse.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greenhouse.R;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.ui.activity.IntroductionActivity;
import com.example.greenhouse.ui.adapter.OptionsAdapter;
import com.example.greenhouse.ui.modal.AddUserModal;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private RecyclerView settingsRecyclerView;
    private OptionsAdapter optionsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsRecyclerView = rootView.findViewById(R.id.settingsRV);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> optionsList = new ArrayList<>();


        optionsList.add("Sign out");

        optionsAdapter = new OptionsAdapter(optionsList, position -> onItemClick(position));
        settingsRecyclerView.setAdapter(optionsAdapter);

        return rootView;
    }

    private void onItemClick(int position) {
        if (position == 0) {
            signOut();
        }
    }

    private void showAddUserModal() {
        AddUserModal addUserModal = new AddUserModal();
        addUserModal.show(getChildFragmentManager(), "addUserModal");
    }

    private void signOut() {
        MockApiService.setMockToken(null);
        Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(requireContext(), IntroductionActivity.class);
        startActivity(intent);

        requireActivity().finish();
    }

}
