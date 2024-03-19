package com.example.automaticgrocery.data.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;


public class Repository {

    private MyDatabaseHelper myDatabaseHelper;
    private SharedPreferences sharedPreferences;

    private static String current_category;

    private Context context;
    public Repository(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.preference_file_key),Context.MODE_PRIVATE);
        this.context = context;
        if (current_category == null){
            current_category = "הכל";
        }
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






    public void updateProductExpPart1(String internal_reference, int new_amount){
        myDatabaseHelper.updateProductExpPart1(internal_reference,new_amount);
    }

    public void updateProductExpPart2(String internal_reference, int last_date_amount, String last_date, int other_expired_removed_items_amount , int amount){
        myDatabaseHelper.updateProductExpPart2(internal_reference,last_date_amount,last_date,other_expired_removed_items_amount,amount);
    }
}
