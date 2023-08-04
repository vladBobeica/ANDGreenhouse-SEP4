package com.example.greenhouse.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.model.UserModel;
import com.example.greenhouse.ui.activity.IntroductionActivity;
import com.example.greenhouse.ui.adapter.OptionsAdapter;
import com.example.greenhouse.ui.modal.AddUserModal;
import com.example.greenhouse.repository.mock.MockUserRepository;
import com.example.greenhouse.ui.modal.EditProfileModal;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private RecyclerView settingsRecyclerView;
    private OptionsAdapter optionsAdapter;

    private TextView usernameTextView;
    private TextView emailTextView;

    private CardView profileCardView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsRecyclerView = rootView.findViewById(R.id.settingsRV);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> optionsList = new ArrayList<>();
        optionsList.add("Sign out");

        optionsAdapter = new OptionsAdapter(optionsList, this::onItemClick);
        settingsRecyclerView.setAdapter(optionsAdapter);


        usernameTextView = rootView.findViewById(R.id.usernameTextView);
        emailTextView = rootView.findViewById(R.id.emailTextView);


        String loggedInUserId = MockApiService.getMockToken();


        UserModel loggedInUser = fetchLoggedInUser(loggedInUserId);


        if (loggedInUser != null) {
            String fullName = loggedInUser.getFirstName() + " " + loggedInUser.getLastName();
            usernameTextView.setText(fullName);
            emailTextView.setText(loggedInUser.getEmail());
        }

        profileCardView = rootView.findViewById(R.id.profileCardView);
        profileCardView.setOnClickListener(view1 -> {
            showEditProfileDialog();
        });

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

    private UserModel fetchLoggedInUser(String userId) {
        return MockUserRepository.getUserById(userId);
    }

    private void showEditProfileDialog() {
       EditProfileModal editProfileModal = new EditProfileModal();
        editProfileModal.show(getChildFragmentManager(), "EditProfileModal");
    }
}
