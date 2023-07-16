package com.example.greenhouse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greenhouse.R;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder> {

    private List<String> optionsList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public OptionsAdapter(List<String> optionsList, OnItemClickListener onItemClickListener) {
        this.optionsList = optionsList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_settings, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String option = optionsList.get(position);
        holder.optionTextView.setText(option);

        holder.cardView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    static class OptionViewHolder extends RecyclerView.ViewHolder {
        TextView optionTextView;
        CardView cardView;

        OptionViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.settingsCardView);
            optionTextView = itemView.findViewById(R.id.optionTextView);
        }
    }
}
