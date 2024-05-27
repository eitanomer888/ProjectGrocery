package com.example.automaticgrocery.data.Repository;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.BroadcastReceiver.AlarmReceiver;
import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;


public class Repository {

    private MyDatabaseHelper myDatabaseHelper;
    private SharedPreferences sharedPreferences;
    private FireBaseHelper myFirebaseHelper;

    private static String current_category;

    private Context context;
    public Repository(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.preference_file_key),Context.MODE_PRIVATE);
        this.context = context;
        if (current_category == null){
            current_category = "הכל";
        }
        myFirebaseHelper = new FireBaseHelper(context);
    }
    public Context getContext() {
        return context;
    }

    public String getCurrent_category() {
        return current_category;
    }

    public void setCurrent_category(String current_category) {
        this.current_category = current_category;
    }

    //FireBase////////////////

    public void LoginConfirm(String username,String password, FireBaseHelper.SearchComplete callback) { myFirebaseHelper.LoginConfirm(username,password,callback);}

    public void DataConfirm(String username,String password, FireBaseHelper.ScanComplete callback){myFirebaseHelper.DataConfirm(username,password,callback);}

    public void AddUser(String username, String password, FireBaseHelper.AddComplete callback){myFirebaseHelper.AddUser(username, password, callback);}

    public void DeleteUser(FireBaseHelper.DeleteComplete callback){myFirebaseHelper.DeleteUser(callback);}

    public void UpdateUser(String fireId, String username, String password, FireBaseHelper.UpdateComplete callback){myFirebaseHelper.UpdateUser(fireId,username, password, callback);}
    ///////////////////////////

    //user actions mydatabasehelper//
    public Cursor readData(){
        return myDatabaseHelper.readAllData();
    }

    public void addUser(String name, String pass){

        myDatabaseHelper.addUser(name,pass);
    }

    public void deleteAllUsers(){
        myDatabaseHelper.deleteAllData();
        WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
        WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
        WriteBooleanToSharedPreferences(String.valueOf(R.string.user_loggedIn_key),false);
    }

    public void deleteOneRowUser(String username){myDatabaseHelper.deleteOneRowUser(username);}

    public boolean updateUserData(String current_name, String name, String pass){return myDatabaseHelper.updateUserData(current_name,name,pass);}
    //###############//

    //Shared preference//
    public void WriteStringToSharedPreferences(String key,String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String ReadStringFromSharedPreferences(String key,String defaultValue)
    {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void WriteBooleanToSharedPreferences(String key,boolean value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean ReadBooleanFromSharedPreferences(String key,boolean defaultValue)
    {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    //###############//

    //product actions mydatabasehelper//
    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category,int target_amount,int last_date_amount){
        myDatabaseHelper.addProduct(internal_reference,name,barcode,amount,fill_date,last_date,category,target_amount,last_date_amount);
    }

    public void DeleteAllProducts(){myDatabaseHelper.deleteAllProducts();}

    public Cursor getAllProducts(){return myDatabaseHelper.getAllProducts();}

    public Cursor getProductsByCategory(String category){return myDatabaseHelper.getProductsByCategory(category);}

    public Cursor getProductsByCategoryAndString(String category, String str){return myDatabaseHelper.getProductsByCategoryAndString(category,str);}




    public void updateProductExpPart1(String internal_reference, int new_amount){
        myDatabaseHelper.updateProductExpPart1(internal_reference,new_amount);
    }
    public void updateProductExpPart1_0(String internal_reference, int last_date_amount, String last_date){myDatabaseHelper.updateProductExpPart1_0(internal_reference, last_date_amount, last_date);}

    public void updateProductExpPart2(String internal_reference, int last_date_amount, String last_date, int other_expired_removed_items_amount , int amount){
        myDatabaseHelper.updateProductExpPart2(internal_reference,last_date_amount,last_date,other_expired_removed_items_amount,amount);
    }

    public void updateProductFill(String internal_reference, int current_amount, String last_date, String fill_date , int new_filled_amount){
        myDatabaseHelper.updateProductFill(internal_reference, current_amount, last_date, fill_date, new_filled_amount);
    }

    public void updateProductAll(String internal_reference, int current_amount, String new_date, String last_date , int last_date_amount){
        myDatabaseHelper.updateProductAll(internal_reference, current_amount, new_date, last_date, last_date_amount);
    }

    //alarmManagement
    public void scheduleAlarm() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        long intervalMillis =   60 * 60 * 1000; // 1 hour in milliseconds
        long triggerTime = System.currentTimeMillis() + intervalMillis;

        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }
    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

}
