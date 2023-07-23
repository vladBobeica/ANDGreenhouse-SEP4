package com.example.greenhouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class RecommendedMeasurementsAdapter extends RecyclerView.Adapter<RecommendedMeasurementsAdapter.RecommendedMeasurementsViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private List<RecommendedMeasurementsModel> recommendedMeasurements;


    public RecommendedMeasurementsAdapter(List<RecommendedMeasurementsModel> recommendedMeasurements, RecyclerViewInterface recyclerViewInterface){
        this.recommendedMeasurements = recommendedMeasurements;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecommendedMeasurementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_recommended_measurements, parent, false);
        return new RecommendedMeasurementsViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedMeasurementsViewHolder holder, int position) {
        RecommendedMeasurementsModel recommendedMeasurement = recommendedMeasurements.get(position);
        holder.bind(recommendedMeasurement);
    }

    @Override
    public int getItemCount() {
        return recommendedMeasurements.size();
    }


    public static class RecommendedMeasurementsViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;


        public RecommendedMeasurementsViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recommendedMeasurementsTextView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
                }
        public void bind(RecommendedMeasurementsModel recommendedMeasurementsModel) {
            titleTextView.setText(recommendedMeasurementsModel.getTitle());


        }
    }
}

