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


        TextView tvDExpLastDate = dialog.findViewById(R.id.tvDExpLastDate);
        TextView tvDExpProductLastDate = dialog.findViewById(R.id.tvDExpProductLastDate);
        tvDExpProductLastDate.setText(tvExpDate.getText().toString());

        TextView tvDExpAmount =dialog.findViewById(R.id.tvDExpAmount);
        EditText etDExpProductAmount = dialog.findViewById(R.id.etDExpProductAmount);
        etDExpProductAmount.setText(tvExpAmount.getText().toString());

        Button btnDExpConfirm =dialog.findViewById(R.id.btnDExpConfirm);
        Button btnDExpUpdate = dialog.findViewById(R.id.btnDExpUpdate);

        btnDExpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConDialog();
            }
        });

        btnDExpUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpDialog();
            }
        });


        dialog.show();
    }


    public void openUpDialog()
    {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.custom_exp_update_date_dialog);

        TextView tvDUPEXPProductName = dialog.findViewById(R.id.tvDUPEXPProductName);
        tvDUPEXPProductName.setText(tvExpName.getText().toString());

        EditText etDUPEXPProductAmount = dialog.findViewById(R.id.etDUPEXPProductAmount);

        Button btnDUPEXPProductLastDate = dialog.findViewById(R.id.btnDUPEXPProductLastDate);
        TextView etDExpProductLastDate = dialog.findViewById(R.id.etDExpProductLastDate);


        Button btnDUPEXPCancle = dialog.findViewById(R.id.btnDUPEXPCancle);
        Button btnDUPEXPConfirm = dialog.findViewById(R.id.btnDUPEXPConfirm);

        btnDUPEXPProductLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "scanning...", Toast.LENGTH_SHORT).show();
            }
        });

        btnDUPEXPCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnDUPEXPConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "comfirm", Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();
    }


    public void openConDialog(){
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.custom_exp_confirm_data_dialog);

        TextView tvConfirmRemove = dialog.findViewById(R.id.tvConfirmRemove);

        Button btnConfirmCancel = dialog.findViewById(R.id.btnConfirmCancel);
        Button btnConfirmOK = dialog.findViewById(R.id.btnConfirmOK);

        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirmOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        dialog.show();
    }
}
