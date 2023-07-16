package com.example.greenhouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenhouseModel;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.ArrayList;

public class GH_RecycleViewAdapter extends RecyclerView.Adapter<GH_RecycleViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;


    Context context;
    ArrayList<GreenhouseModel> greenhouseModel;

    public GH_RecycleViewAdapter(Context context, ArrayList<GreenhouseModel> greenhouseModel, RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.greenhouseModel = greenhouseModel;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public GH_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new GH_RecycleViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GH_RecycleViewAdapter.MyViewHolder holder, int position) {
    //assigning values to views
        //based on position

        holder.greenhouseName.setText(greenhouseModel.get(position).getGreenhouseName());
    }

    @Override
    public int getItemCount() {
        return greenhouseModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView greenhouseName;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            greenhouseName = itemView.findViewById(R.id.greenhouseNameTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
