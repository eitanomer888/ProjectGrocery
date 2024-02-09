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
}
