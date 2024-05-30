package com.example.automaticgrocery.UI.MyRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Items.AllItem;
import com.example.automaticgrocery.data.Items.FillItem;

import java.util.List;

public class MyAllAdapter extends RecyclerView.Adapter<MyAllViewHolder> {
    Context context;
    List<AllItem> items;

    TextView etSearchProduct;

    //Constructor
    public MyAllAdapter(Context context, List<AllItem> items,TextView etSearchProduct) {
        this.context = context;
        this.items = items;
        this.etSearchProduct = etSearchProduct;
    }



    @NonNull
    @Override
    public MyAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAllViewHolder(LayoutInflater.from(context).inflate(R.layout.single_all_item,parent,false), parent.getContext());
    }

    //initialize parameters
    @Override
    public void onBindViewHolder(@NonNull MyAllViewHolder holder, int position) {
        holder.internal_reference = items.get(position).getInternal_reference();
        holder.name = items.get(position).getName();
        holder.barcode = items.get(position).getBarcode();
        holder.amount = items.get(position).getAmount();
        holder.fill_date = items.get(position).getFill_date();
        holder.last_date = items.get(position).getLast_date();
        holder.category = items.get(position).getCategory();
        holder.target_amount = items.get(position).getTarget_amount();
        holder.last_date_amount = items.get(position).getLast_date_amount();

        holder.tvAllName.setText(items.get(position).getName());
        holder.tvAllInternalReference.setText(items.get(position).getInternal_reference());

        holder.etSearchProduct = etSearchProduct;
    }

    //return number of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}
