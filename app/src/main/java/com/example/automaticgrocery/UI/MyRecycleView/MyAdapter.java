package com.example.automaticgrocery.UI.MyRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Items.ExpiredItem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<ExpiredItem> items;

    public MyAdapter(Context context, List<ExpiredItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_expired_item,parent,false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvExpName.setText(items.get(position).getName());
        holder.tvExpAmount.setText(items.get(position).getLast_date_amount() + " ");
        holder.tvExpDate.setText(items.get(position).getLast_date());
        holder.internal_reference = items.get(position).getInternal_reference();
        holder.amount = items.get(position).getAmount();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
