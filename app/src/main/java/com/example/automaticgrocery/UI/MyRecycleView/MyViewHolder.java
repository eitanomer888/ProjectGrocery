package com.example.automaticgrocery.UI.MyRecycleView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvExpName,tvExpAmount,tvExpDate;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvExpName = itemView.findViewById(R.id.tvExpName);
        tvExpAmount = itemView.findViewById(R.id.tvExpAmount);
        tvExpDate = itemView.findViewById(R.id.tvExpDate);

    }
}
