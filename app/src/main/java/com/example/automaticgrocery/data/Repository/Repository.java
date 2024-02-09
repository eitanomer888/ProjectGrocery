package com.example.automaticgrocery.data.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;


public class Repository {

    private MyDatabaseHelper myDatabaseHelper;
    private SharedPreferences sharedPreferences;
    public Repository(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.preference_file_key),Context.MODE_PRIVATE);
    }
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


}
