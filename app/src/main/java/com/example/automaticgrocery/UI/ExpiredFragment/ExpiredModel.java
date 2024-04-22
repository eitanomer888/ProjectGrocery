package com.example.automaticgrocery.UI.ExpiredFragment;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpiredModel
{
    private Repository repository;

    private Calendar calendar;
    public ExpiredModel(Context context){
        repository = new Repository(context);
        calendar =  Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
    }

    public Cursor getAllProducts(){return repository.getAllProducts();}

    public Cursor getProductsByCategory(){return repository.getProductsByCategory(getCurrent_category());}

    public String getCurrent_category(){return repository.getCurrent_category();}

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

    public boolean isExpired(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String today = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)) + "/" + calendar.get(Calendar.YEAR);
        try {
            Date date1 = dateFormat.parse(today);
            Date date2 = dateFormat.parse(date);

            long differenceInMillis = date2.getTime() - date1.getTime();
            return differenceInMillis < 0;
        } catch (Exception e) {
            return false;
        }
    }
}
