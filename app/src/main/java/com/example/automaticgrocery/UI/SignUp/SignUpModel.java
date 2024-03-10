package com.example.automaticgrocery.UI.SignUp;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class SignUpModel {

    private Repository repository;
    public SignUpModel(Context context){
        repository = new Repository(context);
    }

    public boolean sighUpValidation(String name,String password)
    {
        if(name.equals(""))
            return false;
        if(password.equals(""))
            return false;

        return true;
    }

    public boolean checkForDuplicate(String name)
    {
        Cursor cursor = repository.readData();
        int n = cursor.getCount();
        cursor.moveToFirst();
        for (int i = 0; i < n; i++)
        {
            if(name.equals(cursor.getString(0)))
                return false;
        }

        return true;
    }

    public void addUser(String name,String pass){
        repository.addUser(name,pass);
    }

}
