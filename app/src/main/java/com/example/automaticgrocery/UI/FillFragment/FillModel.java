package com.example.automaticgrocery.UI.FillFragment;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class FillModel
{
    private Repository repository;

    public FillModel (Context context){
        repository = new Repository(context);
    }

    public Cursor getAllProducts(){return repository.getAllProducts();}

    public Cursor getProductsByCategory(){return repository.getProductsByCategory(getCurrent_category());}

    public String getCurrent_category(){return repository.getCurrent_category();}

    public boolean isFillNeeded(int target,int current){
        return current / target < 0.60;
    }
}
