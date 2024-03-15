package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Repository.Repository;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvExpName,tvExpAmount,tvExpDate;

    private Repository repository;
    public MyViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        repository = new Repository(context);

        tvExpName = itemView.findViewById(R.id.tvExpName);
        tvExpAmount = itemView.findViewById(R.id.tvExpAmount);
        tvExpDate = itemView.findViewById(R.id.tvExpDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog()
    {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.custom_exp_dialog);

        Button btnDExpCancel = dialog.findViewById(R.id.btnDExpCancel);
        btnDExpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

       TextView tvDExpName =dialog.findViewById(R.id.tvDExpName);
       TextView tvDExpProductName =dialog.findViewById(R.id.tvDExpProductName);
       tvDExpProductName.setText(tvExpName.getText().toString());

        TextView tvDExpAmount =dialog.findViewById(R.id.tvDExpAmount);
        TextView tvDExpProductAmount = dialog.findViewById(R.id.tvDExpProductAmount);


        TextView tvDExpLastDate = dialog.findViewById(R.id.tvDExpLastDate);
        TextView tvDExpProductLastDate = dialog.findViewById(R.id.tvDExpProductLastDate);

        Button btnDExpConfirm =dialog.findViewById(R.id.btnDExpConfirm);
        Button btnDExpUpdate = dialog.findViewById(R.id.btnDExpUpdate);




        dialog.show();
    }
}
