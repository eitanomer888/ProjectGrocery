package com.example.automaticgrocery.data.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;


public class Repository {

    private MyDatabaseHelper myDatabaseHelper;
    private SharedPreferences sharedPreferences;

    private Context context;
    public Repository(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.preference_file_key),Context.MODE_PRIVATE);
        this.context = context;
    }
    public Context getContext() {
        return context;
    }


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
        WriteBooleanToSharedPreferences(String.valueOf(R.string.user_remember_key),false);
    }

    public void deleteOneRowUser(String username){myDatabaseHelper.deleteOneRowUser(username);}
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
    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category){
        myDatabaseHelper.addProduct(internal_reference,name,barcode,amount,fill_date,last_date,category);
    }

    public void DeleteAllProducts(){myDatabaseHelper.deleteAllProducts();}

    public Cursor getAllProducts(){return myDatabaseHelper.getAllProducts();}

    public Cursor getProductsByCategory(String category){return myDatabaseHelper.getProductsByCategory(category);}


}
