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

public class RecommendedMeasurementsAdapter extends RecyclerView.Adapter<RecommendedMeasurementsAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<RecommendedMeasurementsModel> recommendedMeasurements;
    private Context context;

    public RecommendedMeasurementsAdapter(Context context, ArrayList<RecommendedMeasurementsModel> recommendedMeasurements, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recommendedMeasurements = recommendedMeasurements;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecommendedMeasurementsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_recommended_measurements, parent, false);
        return new RecommendedMeasurementsAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedMeasurementsAdapter.MyViewHolder holder, int position) {
        holder.titleTextView.setText(recommendedMeasurements.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return recommendedMeasurements.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView iconImageView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recommendedMeasurementsTextView);
            iconImageView = itemView.findViewById(R.id.recommendedMeasurementsIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

