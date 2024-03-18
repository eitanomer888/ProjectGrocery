package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.Repository.Repository;

public class MyFillViewHolder extends RecyclerView.ViewHolder {

    private Repository repository;
    TextView tvFillName,tvFillAmount,tvFillint;
    ImageView ivFillWarn;
    public MyFillViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        repository = new Repository(context);

        tvFillName = itemView.findViewById(R.id.tvFillName);
        tvFillAmount = itemView.findViewById(R.id.tvFillAmount);
        tvFillint = itemView.findViewById(R.id.tvFillint);
        ivFillWarn = itemView.findViewById(R.id.ivFillWarn);

        //on click method
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

        dialog.setContentView(R.layout.custom_fill_dialog);

        Button btnD_cancel = dialog.findViewById(R.id.btnD_cancel);
        btnD_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tvD_Name = dialog.findViewById(R.id.tvD_Name);
        TextView tvD_ProductName = dialog.findViewById(R.id.tvD_ProductName);
        tvD_ProductName.setText(tvFillName.getText().toString());

        TextView tvD_ProductAmount = dialog.findViewById(R.id.tvD_ProductAmount);
        EditText etD_ProductAmount = dialog.findViewById(R.id.etD_ProductAmount);
        etD_ProductAmount.setText(tvFillAmount.getText().toString());

        TextView tvD_ProductLastDate = dialog.findViewById(R.id.tvD_ProductLastDate);
        Button btnD_ProductLastDate = dialog.findViewById(R.id.btnD_ProductLastDate);
        TextView etD_ProductLastDate = dialog.findViewById(R.id.etD_ProductLastDate);

        TextView tvD_ProductFirstDate = dialog.findViewById(R.id.tvD_ProductFirstDate);
        Button btnD_ProductFirstDate = dialog.findViewById(R.id.btnD_ProductFirstDate);
        TextView etD_ProductFirstDate = dialog.findViewById(R.id.etD_ProductFirstDate);

        TextView tvD_ProductAmountToFill = dialog.findViewById(R.id.tvD_ProductAmountToFill);
        EditText etD_ProductAmountToFill = dialog.findViewById(R.id.etD_ProductAmountToFill);

        Button btnD_update = dialog.findViewById(R.id.btnD_update);


        //scan the lastDate
        btnD_ProductLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "scanning last", Toast.LENGTH_SHORT).show();
            }
        });

        //scan the NewDate
        btnD_ProductFirstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "scanning new", Toast.LENGTH_SHORT).show();
            }
        });

        //update data
        btnD_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etD_ProductAmount.getText().toString().equals("")){
                    Toast.makeText(repository.getContext(), "type current amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etD_ProductLastDate.getText().toString().equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "scan last date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etD_ProductFirstDate.getText().toString().equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "scan new date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etD_ProductAmountToFill.getText().toString().equals("")){
                    Toast.makeText(repository.getContext(), "fill amount cant be blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                int currentAmount = Integer.parseInt(etD_ProductAmount.getText().toString().trim());
                String lastDate = etD_ProductLastDate.getText().toString().trim();
                String firstDate = etD_ProductFirstDate.getText().toString().trim();
                int fillAmount = Integer.parseInt(etD_ProductAmountToFill.getText().toString().trim());

                if(currentAmount <= 0){
                    Toast.makeText(repository.getContext(), "current amount must be higher than 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(fillAmount <= 0){
                    Toast.makeText(repository.getContext(), "fill amount = 0, no need to fill", Toast.LENGTH_SHORT).show();
                    return;
                }

                //update DB + Holder Objects
                Toast.makeText(repository.getContext(), currentAmount + " " + lastDate + "\n" + firstDate + " " + fillAmount, Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();
    }


}
