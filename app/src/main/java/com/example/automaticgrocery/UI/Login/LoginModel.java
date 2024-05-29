package com.example.automaticgrocery.UI.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.widget.Toast;

import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.Repository.Repository;

public class LoginModel {
    Repository repository;
    Context context;
    public LoginModel(Context context)
    {
        repository = new Repository(context);
        this.context = context;
    }

    public boolean loginValidation(String name,String password)
    {
       if(name.equals(""))
       {
           Toast.makeText(context, "fill user name", Toast.LENGTH_SHORT).show();
           return false;
       }
       if(password.equals("")){
           Toast.makeText(context, "fill password", Toast.LENGTH_SHORT).show();
           return false;
       }
        if(password.length() < 4 || password.length() > 12){
            Toast.makeText(context, "password length between 4 to 12", Toast.LENGTH_SHORT).show();
            return false;
        }

       return true;
    }


    public void LoginConfirm(String username,String password, FireBaseHelper.SearchComplete callback) { repository.LoginConfirm(username,password,callback);}


    public void WriteStringToSharedPreferences(String key,String value)
    {
        repository.WriteStringToSharedPreferences(key,value);
    }

    public String ReadStringFromSharedPreferences(String key,String defaultValue)
    {
        return repository.ReadStringFromSharedPreferences(key, defaultValue);
    }

    public void WriteBooleanToSharedPreferences(String key,boolean value)
    {
        repository.WriteBooleanToSharedPreferences(key, value);
    }

    public boolean ReadBooleanFromSharedPreferences(String key,boolean defaultValue)
    {
        return repository.ReadBooleanFromSharedPreferences(key, defaultValue);
    }

}
