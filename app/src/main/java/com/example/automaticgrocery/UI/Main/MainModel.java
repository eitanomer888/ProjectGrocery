package com.example.automaticgrocery.UI.Main;

import android.content.Context;

import com.example.automaticgrocery.data.Repository.Repository;

public class MainModel {
    private Repository repository;
    public MainModel(Context context){
        repository = new Repository(context);
    }

    public void deleteUsersHelper(){
        repository.deleteAllUsers();
    }
}
