package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.UI.UserCenter.UserCenter;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.Calendar;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvExpName,tvExpAmount,tvExpDate;
    public String internal_reference;
    public int amount;

    private Repository repository;
    private Calendar calendar1,calendar2;
    private int y1,m1,d1,y2,m2,d2;

    private Dialog dialog1,dialog2,dialog3;
    public MyViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        repository = new Repository(context);
        internal_reference = "";
        amount = -1;

        y1=0;m1=0;d1=0;y2=0;m2=0;d2=0;
        calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR) + 5,calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
        calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR),calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));

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
                DatePickerDialog datePicker = new DatePickerDialog(repository.getContext());
                datePicker.setCancelable(false);

                datePicker.getDatePicker().setMaxDate(calendar1.getTimeInMillis());
                datePicker.getDatePicker().setMinDate(calendar2.getTimeInMillis());

                if(y1 != 0){
                    datePicker.updateDate(y1,m1,d1);
                }

                datePicker.show();

                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker2, int i, int i1, int i2) {
                        etDExpProductLastDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y1 = i;
                        m1 = i1;
                        d1 = i2;
                    }
                });
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

            }
        });


        dialog2.show();
    }


    public void openConDialog(int last_date_amount,String date){
        if(last_date_amount > amount){
            Toast.makeText(repository.getContext(), "too big", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog3 = new Dialog(repository.getContext());

        dialog3.setContentView(R.layout.custom_exp_confirm_data_dialog);

        TextView tvConfirmRemove = dialog3.findViewById(R.id.tvConfirmRemove);
        tvConfirmRemove.setText("אנא הסר " + last_date_amount + " מוצרים פגי תוקף");

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
                if(internal_reference.equals("")){
                    Toast.makeText(repository.getContext(), "internal_reference is blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(amount == -1)
                {
                    Toast.makeText(repository.getContext(), "amount is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                repository.updateProductExpPart1(internal_reference,amount-last_date_amount);
                amount -= last_date_amount;

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
                DatePickerDialog datePicker = new DatePickerDialog(repository.getContext());
                datePicker.setCancelable(false);

                datePicker.getDatePicker().setMaxDate(calendar1.getTimeInMillis());
                datePicker.getDatePicker().setMinDate(calendar2.getTimeInMillis());

                if(y2 != 0){
                    datePicker.updateDate(y2,m2,d2);
                }

                datePicker.show();

                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker2, int i, int i1, int i2) {
                        etDFINProductLastDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y2 = i;
                        m2 = i1;
                        d2 = i2;
                    }
                });
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
                    int last_date_amount = Integer.parseInt(etFINEXPProductAmount.getText().toString().trim());
                    String lastdate = etDFINProductLastDate.getText().toString().trim();
                    int removeAmount = Integer.parseInt(etFINEXPRemoveAmount.getText().toString().trim());
                    if(last_date_amount > 0 && removeAmount >= 0)
                    {

                        if(last_date_amount + removeAmount > amount){
                            Toast.makeText(repository.getContext(), "מידע שגוי, אנא תקן מידע / עדכן מידע", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            repository.updateProductExpPart2(internal_reference,last_date_amount,lastdate,removeAmount,amount);
                            amount -= removeAmount;
                            dialog.dismiss();
                        }


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
