package com.example.automaticgrocery.UI.FillFragment;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class FillModel
{
    private Repository repository;

    //Constructor
    public FillModel (Context context){
        repository = new Repository(context);
    }

    //get products by category
    public Cursor getProductsByCategory(){return repository.getProductsByCategory(getCurrent_category());}

    //get selected category
    public String getCurrent_category(){return repository.getCurrent_category();}

    //check if fill needed
    public boolean isFillNeeded(int target,int current){
        return current / target < 0.60;
    }
}
