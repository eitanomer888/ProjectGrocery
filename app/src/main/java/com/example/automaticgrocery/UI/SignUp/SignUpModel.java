package com.example.automaticgrocery.UI.SignUp;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.Repository.Repository;

public class SignUpModel {

    private Repository repository;
    private Context context;

    //Constructor
    public SignUpModel(Context context){
        repository = new Repository(context);
        this.context = context;
    }

    //user input data validation
    public boolean sighUpValidation(String name,String password)
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

    //check if username is already exist
    public void SignUpConfirm(String username,String password, FireBaseHelper.ScanComplete callback){repository.DataConfirm(username,password,callback);}

    //add user to firebase
    public void AddUser(String username, String password, FireBaseHelper.AddComplete callback){repository.AddUser(username, password, callback);}

    //getter and setter
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    //##########//


    //shared preferences//
    public void WriteStringToSharedPreferences(String key,String value)
    {
        repository.WriteStringToSharedPreferences(key,value);
    }
    public void WriteBooleanToSharedPreferences(String key,boolean value)
    {
        repository.WriteBooleanToSharedPreferences(key, value);
    }
    //##########//
}
