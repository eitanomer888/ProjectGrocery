package com.example.automaticgrocery.UI.Main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.example.automaticgrocery.data.BroadcastReceiver.AlarmReceiver;
import com.example.automaticgrocery.data.Repository.Repository;

public class MainModel {

    //repository instance for communication
    private Repository repository;

    //static helper for current fragment
    public static boolean isFill;

    //Constructor
    public MainModel(Context context){
        repository = new Repository(context);
        isFill=true;
    }

    //sharedPreferences actions//
    public boolean ReadBooleanFromSharedPreferences(String key,boolean defaultValue)
    {
        return repository.ReadBooleanFromSharedPreferences(key, defaultValue);
    }

    //######################//

    //add new product
    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category,int target_amount,int last_date_amount){
        repository.addProduct(internal_reference,name,barcode,amount,fill_date,last_date,category,target_amount,last_date_amount);
    }

    //delete all products
    public void DeleteAllProducts(){repository.DeleteAllProducts();}

    //set current category
    public void setCurrent_category(String category)
    {
        repository.setCurrent_category(category);
    }

    //start alarm + notifications service
    public void scheduleAlarm() {
        repository.scheduleAlarm();
    }

}
