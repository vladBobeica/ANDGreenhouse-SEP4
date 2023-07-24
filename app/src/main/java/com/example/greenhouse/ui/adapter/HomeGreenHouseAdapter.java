package com.example.greenhouse.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.List;

public class HomeGreenHouseAdapter extends RecyclerView.Adapter<HomeGreenHouseAdapter.HomeGreenHouseViewHolder> {
    private List<GreenHouseModel> greenhouses;
    private final RecyclerViewInterface recyclerViewInterface;



    public HomeGreenHouseAdapter(List<GreenHouseModel> greenhouses, RecyclerViewInterface recyclerViewInterface) {
        this.greenhouses = greenhouses;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public HomeGreenHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_home_gh, parent, false);
        return new HomeGreenHouseViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGreenHouseViewHolder holder, int position) {
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

    static class HomeGreenHouseViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView addressTextView;

        public HomeGreenHouseViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.greenhouseNameTextView);
             addressTextView = itemView.findViewById(R.id.greenhouseAddressTextView);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (recyclerViewInterface != null){
                         int pos = getAdapterPosition();

                         if(pos != RecyclerView.NO_POSITION){
                             recyclerViewInterface.onItemClick(pos);
                         }
                     }
                 }
             });
        }

        public void bind(GreenHouseModel greenhouse) {
            nameTextView.setText(greenhouse.getName());
            addressTextView.setText(greenhouse.getAddress());
        }
    }
}
