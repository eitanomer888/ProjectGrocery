package com.example.automaticgrocery.UI.Main;

import android.content.Context;

import com.example.automaticgrocery.data.Repository.Repository;

public class MainModel {
    private Repository repository;

    public static boolean isFill;
    public MainModel(Context context){
        repository = new Repository(context);
        isFill=true;
    }

    public void deleteUsersHelper(){
        repository.deleteAllUsers();
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
