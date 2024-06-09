package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.ExpiredFragment.ExpiredFragment;
import com.example.automaticgrocery.UI.FillFragment.FillFragment;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.Repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyFillViewHolder extends RecyclerView.ViewHolder {

    //repository instance for communication
    private Repository repository;

    //calendars for date picker
    private Calendar calendar1,calendar2;

    //date variables
    private int y1,m1,d1,y2,m2,d2;

    //dialog
    private Dialog dialog1;

    //UI components
    TextView tvFillName,tvFillAmount,tvFillint;
    ImageView ivFillWarn;

    //variables
    public int targetAmount;
    String internal_reference;


    //Constructor
    public MyFillViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        repository = new Repository(context);

        y1=0;m1=0;d1=0;y2=0;m2=0;d2=0;
        calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR) + 5,calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
        calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR),calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));

        targetAmount = -1;

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

    //dialogs for fill actions//
    public void openDialog()
    {
        dialog1 = new Dialog(repository.getContext());

        dialog1.setContentView(R.layout.custom_fill_dialog);

        Button btnD_cancel = dialog1.findViewById(R.id.btnD_cancel);
        btnD_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        TextView tvD_Name = dialog1.findViewById(R.id.tvD_Name);
        TextView tvD_ProductName = dialog1.findViewById(R.id.tvD_ProductName);
        tvD_ProductName.setText(tvFillName.getText().toString());

        TextView tvD_ProductAmount = dialog1.findViewById(R.id.tvD_ProductAmount);
        EditText etD_ProductAmount = dialog1.findViewById(R.id.etD_ProductAmount);
        etD_ProductAmount.setText(tvFillAmount.getText().toString());

        TextView tvD_ProductLastDate = dialog1.findViewById(R.id.tvD_ProductLastDate);
        Button btnD_ProductLastDate = dialog1.findViewById(R.id.btnD_ProductLastDate);
        TextView etD_ProductLastDate = dialog1.findViewById(R.id.etD_ProductLastDate);

        TextView tvD_ProductFirstDate = dialog1.findViewById(R.id.tvD_ProductFirstDate);
        Button btnD_ProductFirstDate = dialog1.findViewById(R.id.btnD_ProductFirstDate);
        TextView etD_ProductFirstDate = dialog1.findViewById(R.id.etD_ProductFirstDate);

        Button btnD_update = dialog1.findViewById(R.id.btnD_update);


        //scan the lastDate
        btnD_ProductLastDate.setOnClickListener(new View.OnClickListener() {
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
                        etD_ProductLastDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y1 = i;
                        m1 = i1;
                        d1 = i2;
                    }
                });
            }
        });

        //scan the NewDate
        btnD_ProductFirstDate.setOnClickListener(new View.OnClickListener() {
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
                        etD_ProductFirstDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y2 = i;
                        m2 = i1;
                        d2 = i2;
                    }
                });
            }
        });

        //update data
        btnD_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etD_ProductAmount.getText().toString().equals("")){
                    Toast.makeText(repository.getContext(), "הכנס כמות נוכחית", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etD_ProductLastDate.getText().toString().equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "סרוק תוקף אחרון", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etD_ProductFirstDate.getText().toString().equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "סרוק תוקף למילוי", Toast.LENGTH_SHORT).show();
                    return;
                }

                int currentAmount = 0;
                try {
                    currentAmount = Integer.parseInt(etD_ProductAmount.getText().toString().trim());
                }
                catch (Exception e){
                    Toast.makeText(repository.getContext(), "invalid int", Toast.LENGTH_SHORT).show();
                    return;
                }
                String lastDate = etD_ProductLastDate.getText().toString().trim();
                String firstDate = etD_ProductFirstDate.getText().toString().trim();

                if(currentAmount < 0){
                    Toast.makeText(repository.getContext(), "כמות נוכחית חייב להיות גדולה או שווה לאפס", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(currentAmount > targetAmount){
                    Toast.makeText(repository.getContext(), "כמות גדולה מידי (חורג מן הכמות הרצויה)", Toast.LENGTH_SHORT).show();
                    return;
                }

                //update DB + Holder Objects
                openFinalDialog(currentAmount,lastDate,firstDate);
            }
        });


        dialog1.show();
    }


    public void openFinalDialog(int currentAmount,String last_date, String fill_date)
    {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.custom_fill_pt2_dialog);

        EditText etD_ProductAmountToFill = dialog.findViewById(R.id.etD_ProductAmountToFill);

        etD_ProductAmountToFill.setText(secretFormula(currentAmount,last_date,fill_date) + "");
        if (currentAmount == 0){
            etD_ProductAmountToFill.setText(targetAmount + "");
        }


        Button btnFillBack =dialog.findViewById(R.id.btnFillBack);
        Button btnFillOk =dialog.findViewById(R.id.btnFillOk);

        btnFillBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnFillOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount_to_fill = etD_ProductAmountToFill.getText().toString().trim();
                if (amount_to_fill.equals(""))
                    return;

                int amount_toFill = 0;
                try {
                    amount_toFill = Integer.parseInt(amount_to_fill);
                }
                catch (Exception e){
                    Toast.makeText(repository.getContext(), "invalid int", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (amount_toFill <= 0)
                    return;
                if(amount_toFill > targetAmount - currentAmount){
                    Toast.makeText(repository.getContext(), "יותר מידי מוצרים, פנה לאחראי", Toast.LENGTH_SHORT).show();
                    return;
                }

                //db action
                repository.updateProductFill(internal_reference,currentAmount,last_date,fill_date,amount_toFill);

                dialog.dismiss();
                if(dialog1 != null)
                    if(dialog1.isShowing())
                        dialog1.dismiss();

                FillFragment fillFragment = new FillFragment();
                ((AppCompatActivity)repository.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fillFragment).commit();

            }
        });





        dialog.show();
    }

    //#######################//



    //difference in days between two dates
    public static double getDifferenceInDays(String dateString1, String dateString2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(dateString2);

            long differenceInMillis = date2.getTime() - date1.getTime();
            double differenceInDays = (double) (differenceInMillis / (1000 * 60 * 60 * 24));
            return differenceInDays;
        } catch (Exception e) {
            return -1.0;
        }
    }


    //formula for calculating amount to fill
    public int secretFormula(int currentAmount,String last_date, String fill_date)
    {
        String today = calendar2.get(Calendar.DAY_OF_MONTH) + "/" + (calendar2.get(Calendar.MONTH) + 1) + "/" + calendar2.get(Calendar.YEAR);
        double top = getDifferenceInDays(today , last_date);
        double buttom = Math.abs(getDifferenceInDays(fill_date,last_date));
        double F = top / buttom;
        if (F > 1)
            F = 1;
        else if (F < 0)
            F = 0;
        double answer =  F * (targetAmount - currentAmount);
        answer = Math.round(answer);
        return (int) answer;
    }



}
