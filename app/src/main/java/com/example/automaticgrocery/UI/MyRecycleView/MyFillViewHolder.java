package com.example.automaticgrocery.UI.MyRecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;

public class MyFillViewHolder extends RecyclerView.ViewHolder {

    TextView tvFillName,tvFillAmount,tvFillint;
    ImageView ivFillWarn;
    public MyFillViewHolder(@NonNull View itemView) {
        super(itemView);
        tvFillName = itemView.findViewById(R.id.tvFillName);
        tvFillAmount = itemView.findViewById(R.id.tvFillAmount);
        tvFillint = itemView.findViewById(R.id.tvFillint);
        ivFillWarn = itemView.findViewById(R.id.ivFillWarn);
    }
}
