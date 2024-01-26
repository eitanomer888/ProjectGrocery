package com.example.automaticgrocery.UI.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class LoginModel {
    Repository repository;
    public LoginModel(Context context)
    {
        repository = new Repository(context);
    }

    public boolean loginValidation(String name,String password)
    {
       if(name.equals(""))
           return false;
       if(password.equals(""))
           return false;

       return true;
    }
    public boolean searchUser(String name, String pass)
    {
        Cursor cursor = repository.readData();
        int n = cursor.getCount();
        cursor.moveToFirst();
        for (int i = 0; i < n; i++)
        {
            if(name.equals(cursor.getString(1)) && pass.equals(cursor.getString(2)))
                return true;

            cursor.moveToNext();
        }

        return false;
    }

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
