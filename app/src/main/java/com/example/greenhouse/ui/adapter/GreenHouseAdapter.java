package com.example.greenhouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class GreenHouseAdapter extends RecyclerView.Adapter<GreenHouseAdapter.GreenHouseViewHolder> {
    private List<GreenHouseModel> greenhouses;

    public GreenHouseAdapter(List<GreenHouseModel> greenhouses) {
        this.greenhouses = greenhouses;
    }

    @NonNull
    @Override
    public GreenHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new GreenHouseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GreenHouseViewHolder holder, int position) {
        GreenHouseModel greenhouse = greenhouses.get(position);
        holder.bind(greenhouse);
    }

    @Override
    public int getItemCount() {
        return greenhouses.size();
    }

    public void setGreenhouses(List<GreenHouseModel> greenhouses) {
        this.greenhouses = greenhouses;
        notifyDataSetChanged();
    }

    static class GreenHouseViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView addressTextView;

        public GreenHouseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.greenhouseNameTextView);
//            addressTextView = itemView.findViewById(R.id.addressTextView);
        }

        public void bind(GreenHouseModel greenhouse) {
            nameTextView.setText(greenhouse.getName());
//            addressTextView.setText(greenhouse.getAddress());
        }
    }
}
