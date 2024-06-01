package com.example.automaticgrocery.UI.MyRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Items.ExpiredItem;
import com.example.automaticgrocery.data.Items.FillItem;

import java.util.List;

public class MyFillAdapter extends RecyclerView.Adapter<MyFillViewHolder> {

    //context for recycleView
    Context context;

    //list of fill items
    List<FillItem> items;

    //Constructor
    public MyFillAdapter(Context context, List<FillItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyFillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFillViewHolder(LayoutInflater.from(context).inflate(R.layout.single_fill_item,parent,false), parent.getContext());
    }

    //initialize parameters
    @Override
    public void onBindViewHolder(@NonNull MyFillViewHolder holder, int position) {
        holder.tvFillName.setText(items.get(position).getName());
        holder.tvFillAmount.setText(items.get(position).getAmount() + "");
        holder.tvFillint.setText(items.get(position).getInternal_reference());
        holder.targetAmount = items.get(position).getTargetAmount();
        holder.internal_reference = items.get(position).getInternal_reference();
        //add color options on the ivFillWarn



    }

    //return the number of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}
