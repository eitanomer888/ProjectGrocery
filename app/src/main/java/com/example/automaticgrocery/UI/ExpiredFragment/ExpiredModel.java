package com.example.automaticgrocery.UI.ExpiredFragment;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpiredModel
{
    //repository instance for communication
    private Repository repository;

    //calender instance
    private Calendar calendar;

    //Constructor
    public ExpiredModel(Context context){
        repository = new Repository(context);
        calendar =  Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
    }

    //get products by category
    public Cursor getProductsByCategory(){return repository.getProductsByCategory(getCurrent_category());}

    //get selected category
    public String getCurrent_category(){return repository.getCurrent_category();}

    //check if product is expired
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
