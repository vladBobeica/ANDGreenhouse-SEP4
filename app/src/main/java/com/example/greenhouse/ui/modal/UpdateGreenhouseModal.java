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
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.data.DbHelper;
import com.example.greenhouse.ui.fragment.DashboardFragment;

public class UpdateGreenhouseModal extends DialogFragment {

    private EditText greenHouseName;
    private EditText greenHouseAddress;

    private Button updateGreenHouseButton;
    private String id;

    private String name;
    private String address;



    public UpdateGreenhouseModal( String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_update_greenhouse, container, false);

        greenHouseName = rootView.findViewById(R.id.greenhouseNameEditText1);
        greenHouseAddress = rootView.findViewById(R.id.greenhouseAddressEditText2);
        updateGreenHouseButton = rootView.findViewById(R.id.updateGreenHouseButton);
        greenHouseName.setText(name);
        greenHouseAddress.setText(address);

        updateGreenHouseButton.setOnClickListener(v -> {
            String updatedName = greenHouseName.getText().toString().trim();
            String updatedAddress = greenHouseAddress.getText().toString().trim();
            int userId = MockApiService.getCurrentUserId();

            if (!updatedName.isEmpty()) {
                DbHelper myDB = new DbHelper(requireContext());
                myDB.updateData(id, updatedName, updatedAddress); // Pass the updated values here

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
        dialog.setContentView(R.layout.modal_update_greenhouse);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }



}