package com.example.greenhouse.ui.modal;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.greenhouse.R;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.model.UserModel;
import com.example.greenhouse.repository.UserRepository;
import com.example.greenhouse.repository.mock.MockUserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileModal extends DialogFragment {

    private EditText userFirstName;
    private EditText userLastName;
    private EditText userEmail;

    private UserRepository repository;
    private UserModel loggedInUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_update_profile, container, false);

        userEmail = rootView.findViewById(R.id.emailEditTextUpdateProfile);
        userLastName = rootView.findViewById(R.id.lastNameEditTextUpdateProfile);
        userFirstName = rootView.findViewById(R.id.firstNameEditTextUpdateProfile);
        repository = new UserRepository();
        String loggedInUserId = MockApiService.getMockToken();
        loggedInUser = MockUserRepository.getUserById(loggedInUserId);

        if (loggedInUser != null) {
            userFirstName.setText(loggedInUser.getFirstName());
            userLastName.setText(loggedInUser.getLastName());
            userEmail.setText(loggedInUser.getEmail());
        }

        Button saveUserButton = rootView.findViewById(R.id.saveButtonUpdateProfile);
        saveUserButton.setOnClickListener(v -> {
            Log.d("User", "Update profile button tapped");
            saveChangesSettings();
        });

        return rootView;
    }

    private void saveChangesSettings() {
        if (loggedInUser == null) {
            return;
        }

        String email = userEmail.getText().toString();
        String firstName = userFirstName.getText().toString();
        String lastName = userLastName.getText().toString();

        UserModel updatedUser = new UserModel(
                loggedInUser.getId(),
                email,
                firstName,
                lastName
        );

        Log.d("User", "Updated User: " + updatedUser.toString());

        repository.updateUserProfile(updatedUser, new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "User profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("User", "Failed to update user profile. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("User", "Failed to update user profile. Error: " + t.getMessage());
            }
        });
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_update_profile);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }
}
