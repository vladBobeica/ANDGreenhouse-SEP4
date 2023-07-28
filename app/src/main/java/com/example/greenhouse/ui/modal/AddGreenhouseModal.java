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

public class AddGreenhouseModal extends DialogFragment {

    private EditText greenHouseName;
    private EditText greenHouseAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_add_greenhouse, container, false);

        greenHouseName = rootView.findViewById(R.id.greenhouseNameEditText);
        greenHouseAddress = rootView.findViewById(R.id.greenhouseAddressEditText);
        Button addGreenHouseButton = rootView.findViewById(R.id.addGreenHouseButton);
        addGreenHouseButton.setOnClickListener(v -> {
            String name = greenHouseName.getText().toString().trim();
            String address = greenHouseAddress.getText().toString().trim();
            if (!name.isEmpty()) {
                // Logic here
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please enter the greenhouse name", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_add_greenhouse);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }
}
