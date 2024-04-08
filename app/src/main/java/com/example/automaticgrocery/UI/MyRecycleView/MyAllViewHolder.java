package com.example.automaticgrocery.UI.MyRecycleView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Repository.Repository;

public class MyAllViewHolder extends RecyclerView.ViewHolder {
    private Repository repository;

    public String internal_reference,name,barcode;
    public int amount;
    public String fill_date,last_date,category;
    public int target_amount,last_date_amount;

    TextView tvAllName, tvAllInternalReference;
    public MyAllViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        repository = new Repository(context);

        tvAllName = itemView.findViewById(R.id.tvAllName);
        tvAllInternalReference = itemView.findViewById(R.id.tvAllInternalReference);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
