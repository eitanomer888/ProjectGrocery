package com.example.automaticgrocery.UI.MyRecycleView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyAllViewHolder extends RecyclerView.ViewHolder {

    //repository instance for communication with data
    private Repository repository;

    //calendar for dates
    private Calendar calendar1,calendar2;

    //date variables
    private int y1,m1,d1,y2,m2,d2;

    //UI components
    private Dialog dialog1;
    TextView tvAllName, tvAllInternalReference;
    TextView etSearchProduct;

    //variables

    public String internal_reference,name,barcode;
    public int amount;
    public String fill_date,last_date,category;
    public int target_amount,last_date_amount;


    //Constructor
    public MyAllViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        repository = new Repository(context);
        y1=0;m1=0;d1=0;y2=0;m2=0;d2=0;
        calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR) + 5,calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
        calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR),calendar2.get(Calendar.MONTH),calendar2.get(Calendar.DAY_OF_MONTH));

        tvAllName = itemView.findViewById(R.id.tvAllName);
        tvAllInternalReference = itemView.findViewById(R.id.tvAllInternalReference);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    //dialog for editing product
    public void openDialog()
    {
        dialog1 = new Dialog(repository.getContext());

        dialog1.setContentView(R.layout.custom_all_dialog);


        Button btnD_back = dialog1.findViewById(R.id.btnD_back);
        btnD_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                etSearchProduct.setText("");
            }
        });

        TextView tvDP_Name = dialog1.findViewById(R.id.tvDP_Name);
        tvDP_Name.setText(tvAllName.getText().toString().trim());

        EditText etDP_CurrentAmount = dialog1.findViewById(R.id.etDP_CurrentAmount);
        etDP_CurrentAmount.setText(amount + "");

        Button btnDP_FillDate = dialog1.findViewById(R.id.btnDP_FillDate);
        TextView etDP_FillDate = dialog1.findViewById(R.id.etDP_FillDate);
        etDP_FillDate.setText(fill_date);
        btnDP_FillDate.setOnClickListener(new View.OnClickListener() {
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
                        etDP_FillDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y1 = i;
                        m1 = i1;
                        d1 = i2;
                    }
                });
            }
        });

        Button btnDP_LastDate= dialog1.findViewById(R.id.btnDP_LastDate);
        TextView etDP_LastDate = dialog1.findViewById(R.id.etDP_LastDate);
        etDP_LastDate.setText(last_date);
        btnDP_LastDate.setOnClickListener(new View.OnClickListener() {
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
                        etDP_LastDate.setText(i2 + "/" + (i1+1) + "/" + i);
                        y2 = i;
                        m2 = i1;
                        d2 = i2;
                    }
                });
            }
        });


        EditText etDP_LastDateAmount = dialog1.findViewById(R.id.etDP_LastDateAmount);
        etDP_LastDateAmount.setText(last_date_amount + "");

        Button btnDP_Confirm = dialog1.findViewById(R.id.btnDP_Confirm);
        btnDP_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_date = etDP_FillDate.getText().toString().trim();
                String last_date = etDP_LastDate.getText().toString().trim();
                if(etDP_CurrentAmount.getText().toString().equals("")){
                    Toast.makeText(repository.getContext(), "אנא מלא כמות נוכחית", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(new_date.equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "סרוק תאריך חדש", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(last_date.equals("אנא סרוק תאריך")){
                    Toast.makeText(repository.getContext(), "סרוק תאריך אחרון", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etDP_LastDateAmount.getText().toString().equals("")){
                    Toast.makeText(repository.getContext(), "אנא מלא כמות של תוקף אחרון", Toast.LENGTH_SHORT).show();
                    return;
                }
                int amountC = 0; int amountLD = 0;
                try {
                    amountC = Integer.parseInt(etDP_CurrentAmount.getText().toString().trim());
                    amountLD = Integer.parseInt(etDP_LastDateAmount.getText().toString().trim());
                }
                catch (Exception e){
                    Toast.makeText(repository.getContext(), "invalid int", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(amountC < 0 || amountLD < 0){
                    Toast.makeText(repository.getContext(), "כמות צריכה להיות חיובית או אפס", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (amountC < amountLD){
                    Toast.makeText(repository.getContext(), "שגיאה בנתוני הכמות", Toast.LENGTH_SHORT).show();
                    return;
                }

                double d = getDifferenceInDays(etDP_LastDate.getText().toString().trim(),etDP_FillDate.getText().toString().trim());
                if (d < 0){
                    Toast.makeText(repository.getContext(), "תאריך חדש חייב להיות גדול או שווה לתאריך אחרון", Toast.LENGTH_SHORT).show();
                    return;
                }


                repository.updateProductAll(internal_reference,amountC,new_date,last_date,amountLD);

                dialog1.dismiss();
                etSearchProduct.setText("");
            }
        });









        dialog1.show();
    }

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

}
