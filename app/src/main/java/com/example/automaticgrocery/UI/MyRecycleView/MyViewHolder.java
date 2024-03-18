package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.UI.UserCenter.UserCenter;
import com.example.automaticgrocery.data.Repository.Repository;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvExpName,tvExpAmount,tvExpDate;

    private Repository repository;

    private Dialog dialog1,dialog2,dialog3;
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
        dialog1 = new Dialog(repository.getContext());

        dialog1.setContentView(R.layout.custom_exp_dialog);

        Button btnDExpCancel = dialog1.findViewById(R.id.btnDExpCancel);
        btnDExpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

       TextView tvDExpName =dialog1.findViewById(R.id.tvDExpName);
       TextView tvDExpProductName =dialog1.findViewById(R.id.tvDExpProductName);
       tvDExpProductName.setText(tvExpName.getText().toString().trim());


        TextView tvDExpLastDate = dialog1.findViewById(R.id.tvDExpLastDate);
        TextView tvDExpProductLastDate = dialog1.findViewById(R.id.tvDExpProductLastDate);
        tvDExpProductLastDate.setText(tvExpDate.getText().toString().trim());

        TextView tvDExpAmount =dialog1.findViewById(R.id.tvDExpAmount);
        EditText etDExpProductAmount = dialog1.findViewById(R.id.etDExpProductAmount);
        etDExpProductAmount.setText(tvExpAmount.getText().toString().trim());

        Button btnDExpConfirm =dialog1.findViewById(R.id.btnDExpConfirm);
        Button btnDExpUpdate = dialog1.findViewById(R.id.btnDExpUpdate);

        btnDExpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDExpProductAmount.getText().toString().trim().equals(""))
                {
                    Toast.makeText(repository.getContext(), "אנא מלא את שדה הכמות", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int amount = Integer.parseInt(etDExpProductAmount.getText().toString().trim());
                    String date = tvDExpProductLastDate.getText().toString().trim();
                    if(amount > 0){
                        openConDialog(amount,date);
                    }
                    else{
                        Toast.makeText(repository.getContext(), "כמות צריכה להיות גדולה מ-0", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnDExpUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpDialog();
            }
        });


        dialog1.show();
    }


    public void openUpDialog()
    {
        dialog2 = new Dialog(repository.getContext());

        dialog2.setContentView(R.layout.custom_exp_update_date_dialog);

        TextView tvDUPEXPProductName = dialog2.findViewById(R.id.tvDUPEXPProductName);
        tvDUPEXPProductName.setText(tvExpName.getText().toString());

        EditText etDUPEXPProductAmount = dialog2.findViewById(R.id.etDUPEXPProductAmount);

        Button btnDUPEXPProductLastDate = dialog2.findViewById(R.id.btnDUPEXPProductLastDate);
        TextView etDExpProductLastDate = dialog2.findViewById(R.id.etDExpProductLastDate);


        Button btnDUPEXPCancle = dialog2.findViewById(R.id.btnDUPEXPCancle);
        Button btnDUPEXPConfirm = dialog2.findViewById(R.id.btnDUPEXPConfirm);

        btnDUPEXPProductLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "scanning...", Toast.LENGTH_SHORT).show();
            }
        });

        btnDUPEXPCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        btnDUPEXPConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDUPEXPProductAmount.getText().toString().trim().equals("")){
                    Toast.makeText(repository.getContext(), "אנא מלא את שדה הכמות", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etDExpProductLastDate.getText().toString().trim().equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "אנא סרוק תוקף אחרון", Toast.LENGTH_SHORT).show();
                    return;
                }
                String last_date = etDExpProductLastDate.getText().toString().trim();
                int amount = Integer.parseInt(etDUPEXPProductAmount.getText().toString().trim());
                if(amount <= 0){
                    Toast.makeText(repository.getContext(), "כמות חייבת להיות גדולה מ-0", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check if date is not pag tokef

                openConDialog(amount,last_date);

                Toast.makeText(repository.getContext(), "comfirm", Toast.LENGTH_SHORT).show();
            }
        });


        dialog2.show();
    }


    public void openConDialog(int amount,String date){
        dialog3 = new Dialog(repository.getContext());

        dialog3.setContentView(R.layout.custom_exp_confirm_data_dialog);

        TextView tvConfirmRemove = dialog3.findViewById(R.id.tvConfirmRemove);
        tvConfirmRemove.setText("אנא הסר " + amount + " מוצרים פגי תוקף");

        Button btnConfirmCancel = dialog3.findViewById(R.id.btnConfirmCancel);
        Button btnConfirmOK = dialog3.findViewById(R.id.btnConfirmOK);

        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.dismiss();
            }
        });

        btnConfirmOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "update all data and views", Toast.LENGTH_SHORT).show();



                openFinDialog();
            }
        });


        dialog3.show();
    }

    public void openFinDialog()
    {
        Dialog dialog = new Dialog(repository.getContext());
        dialog.setCancelable(false);

        dialog.setContentView(R.layout.custom_exp_final_data_dialog);

        TextView tvFINEXPProductName = dialog.findViewById(R.id.tvFINEXPProductName);
        tvFINEXPProductName.setText(tvExpName.getText().toString().trim());

        EditText etFINEXPProductAmount = dialog.findViewById(R.id.etFINEXPProductAmount);

        Button btnFINEXPProductLastDate = dialog.findViewById(R.id.btnFINEXPProductLastDate);
        TextView etDFINProductLastDate = dialog.findViewById(R.id.etDFINProductLastDate);
        btnFINEXPProductLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(repository.getContext(), "scanning...", Toast.LENGTH_SHORT).show();
            }
        });

        EditText etFINEXPRemoveAmount = dialog.findViewById(R.id.etFINEXPRemoveAmount);


        Button btnFINEXPOk =dialog.findViewById(R.id.btnFINEXPOk);
        btnFINEXPOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFINEXPProductAmount.getText().toString().trim().equals(""))
                    Toast.makeText(repository.getContext(), "מלא כמות של תוקף אחרון", Toast.LENGTH_SHORT).show();
                else if(etDFINProductLastDate.getText().toString().trim().equals("אנא סרוק תאריך"))
                    Toast.makeText(repository.getContext(), "סרוק תאריך אחרון", Toast.LENGTH_SHORT).show();
                else if (etFINEXPRemoveAmount.getText().toString().trim().equals(""))
                    Toast.makeText(repository.getContext(), "מלא כמות של פגי תוקף", Toast.LENGTH_SHORT).show();
                else{
                    int amount = Integer.parseInt(etFINEXPProductAmount.getText().toString().trim());
                    String lastdate = etDFINProductLastDate.getText().toString().trim();
                    int removeAmount = Integer.parseInt(etFINEXPRemoveAmount.getText().toString().trim());
                    if(amount > 0 && removeAmount > 0)
                    {







                    }
                    else{
                        Toast.makeText(repository.getContext(), "כמות חייבת להיות גדולה מ-0", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });













        dialog.show();
    }
}
