package com.example.automaticgrocery.UI.ExpiredFragment;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class ExpiredModel
{
    private Repository repository;
    public ExpiredModel(Context context){
        repository = new Repository(context);
    }

    public Cursor getAllProducts(){return repository.getAllProducts();}

    public Cursor getProductsByCategory(){return repository.getProductsByCategory(getCurrent_category());}

    public String getCurrent_category(){return repository.getCurrent_category();}
}
