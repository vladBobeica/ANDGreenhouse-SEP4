package com.example.greenhouse.ui.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterfaceDashboard;

import java.util.List;

public class GreenHouseAdapter extends RecyclerView.Adapter<GreenHouseAdapter.GreenHouseViewHolder> {
    private List<GreenHouseModel> greenhouses;
    private final RecyclerViewInterfaceDashboard recyclerViewInterfaceDashboard;

    public GreenHouseAdapter(List<GreenHouseModel> greenhouses, RecyclerViewInterfaceDashboard recyclerViewInterfaceDashboard) {
        this.greenhouses = greenhouses;
        this.recyclerViewInterfaceDashboard = recyclerViewInterfaceDashboard;
    }

    @NonNull
    @Override
    public GreenHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new GreenHouseViewHolder(itemView, recyclerViewInterfaceDashboard);
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

    public GreenHouseModel getGreenHouse(int position) {
        if (position >= 0 && position < greenhouses.size()) {
            return greenhouses.get(position);
        }
        return null;
    }

    public void setGreenhouses(List<GreenHouseModel> greenhouses) {
        this.greenhouses = greenhouses;
        notifyDataSetChanged();
    }

    public void removeGreenHouse(int greenhouseId) {
        for (int i = 0; i < greenhouses.size(); i++) {
            if (greenhouses.get(i).getId() == greenhouseId) {
                Log.d(TAG, "Removing greenhouse from adapter. Position: " + i + ", greenhouseId: " + greenhouseId);
                greenhouses.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, greenhouses.size());
                break;
            }
        }
    }

    public void addGreenHouse(GreenHouseModel newGreenhouse) {
        greenhouses.add(newGreenhouse);
        notifyItemInserted(greenhouses.size() - 1);
    }


    static class GreenHouseViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView addressTextView;

        public GreenHouseViewHolder(@NonNull View itemView, RecyclerViewInterfaceDashboard recyclerViewInterfaceDashboard) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.greenhouseNameTextView);
            addressTextView = itemView.findViewById(R.id.greenhouseAddressTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterfaceDashboard != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterfaceDashboard.onItemClick(pos);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (recyclerViewInterfaceDashboard != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterfaceDashboard.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }



        public void bind(GreenHouseModel greenhouse) {
            nameTextView.setText(greenhouse.getName());
            addressTextView.setText(greenhouse.getAddress());
        }
    }
}
