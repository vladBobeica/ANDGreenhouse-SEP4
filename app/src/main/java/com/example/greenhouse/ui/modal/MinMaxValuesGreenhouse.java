package com.example.greenhouse.ui.modal;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.greenhouse.R;

public class MinMaxValuesGreenhouse extends DialogFragment {

    private EditText tempMin;
    private EditText tempMax;
    private EditText humidMin;
    private EditText humidMax;
    private EditText lightMin;
    private EditText lightMax;
    private Button saveChanges;

    private Button applyRecommended;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modal_minmax_greenhouse, container, false);
        tempMin = rootView.findViewById(R.id.temperatureMinEditText);
        tempMax = rootView.findViewById(R.id.temperatureMaxEditText);
        humidMin = rootView.findViewById(R.id.humidityMinEditText);
        humidMax = rootView.findViewById(R.id.humidityMaxEditText);
        lightMin = rootView.findViewById(R.id.lightMinEditText);
        lightMax = rootView.findViewById(R.id.lightMaxEditText);
        applyRecommended = rootView.findViewById(R.id.applyRecommendedSettingsButtonComp);
        saveChanges = rootView.findViewById(R.id.saveMinMaxChanges);


           applyRecommended.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("GreenHouse", "Apply button tapped");
                    applyRecommendedSettings();
                }
            });


        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.modal_minmax_greenhouse);



        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return dialog;
    }

    private void applyRecommendedSettings() {

        tempMin.setText("15");
        tempMax.setText("25");

        humidMin.setText("40");
        humidMax.setText("60");

        lightMin.setText("1000");
        lightMax.setText("5000");
    }
}
