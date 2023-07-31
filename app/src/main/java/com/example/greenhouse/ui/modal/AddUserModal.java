package com.example.greenhouse.ui.modal;

import android.app.Dialog;
import android.os.Bundle;
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

public class AddUserModal extends DialogFragment {

    private EditText userEmail;
    private EditText userPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_add_user, container, false);

        userEmail = rootView.findViewById(R.id.addUserEmail);
        userPassword = rootView.findViewById(R.id.addUserPassword);
        Button addUserButton = rootView.findViewById(R.id.addUserButton);
        addUserButton.setOnClickListener(v -> {
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if (!email.isEmpty() || !password.isEmpty()) {

                dismiss();
            } else {
                Toast.makeText(getContext(), "Email or password incorrectly", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_add_user);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }
}
