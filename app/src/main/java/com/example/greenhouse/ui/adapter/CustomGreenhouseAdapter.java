package com.example.greenhouse.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.ui.modal.UpdateGreenhouseModal;

import java.util.ArrayList;

public class CustomGreenhouseAdapter extends RecyclerView.Adapter<CustomGreenhouseAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> greenhouse_id;
    private ArrayList<String> greenhouse_name;
    private ArrayList<String> greenhouse_address;


    public CustomGreenhouseAdapter(Context context, ArrayList<String> greenhouse_id,
                                   ArrayList<String> greenhouse_name, ArrayList<String> greenhouse_address) {
        this.context = context;
        this.greenhouse_id = greenhouse_id;
        this.greenhouse_name = greenhouse_name;
        this.greenhouse_address = greenhouse_address;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.greenhouse_name.setText(greenhouse_name.get(position));
        holder.greenhouse_address.setText(greenhouse_address.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    String id = greenhouse_id.get(clickedPosition);
                    String name = greenhouse_name.get(clickedPosition);
                    String address = greenhouse_address.get(clickedPosition);
                    UpdateGreenhouseModal modal = new UpdateGreenhouseModal(id, name, address);
                    modal.show(((AppCompatActivity) context).getSupportFragmentManager(), "UpdateGreenhouseModal");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return greenhouse_name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView greenhouse_name, greenhouse_address;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            greenhouse_name = itemView.findViewById(R.id.greenhouseNameTextView);
            greenhouse_address = itemView.findViewById(R.id.greenhouseAddressTextView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
