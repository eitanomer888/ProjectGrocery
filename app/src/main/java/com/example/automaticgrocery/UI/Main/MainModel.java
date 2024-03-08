package com.example.automaticgrocery.UI.Main;

import android.content.Context;
import android.database.Cursor;

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

    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category){
        repository.addProduct(internal_reference,name,barcode,amount,fill_date,last_date,category);
    }

    public void DeleteAllProducts(){repository.DeleteAllProducts();}

    public Cursor getAllProducts(){return repository.getAllProducts();}

    public Cursor getProductsByCategory(String category){return repository.getProductsByCategory(category);}
}
