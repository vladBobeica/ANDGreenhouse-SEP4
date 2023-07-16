package com.example.greenhouse.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.MeasurementModel;

import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.ViewHolder> {
    private final List<MeasurementModel> measurements;

    public MeasurementsAdapter(List<MeasurementModel> measurements) {
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_measurement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeasurementModel measurement = measurements.get(position);
        holder.dateTextView.setText(measurement.getDate());
        holder.temperatureTextView.setText(measurement.getTemperature());
        holder.humidityTextView.setText(measurement.getHumidity());
        holder.lightTextView.setText(measurement.getLight());
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView temperatureTextView;
        TextView humidityTextView;
        TextView lightTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            humidityTextView = itemView.findViewById(R.id.humidityTextView);
            lightTextView = itemView.findViewById(R.id.lightTextView);
        }
    }
}
