package com.example.automaticgrocery.UI.AllProducts;

import android.content.Context;
import android.database.Cursor;

import com.example.automaticgrocery.data.Repository.Repository;

public class AllProductsModel {
    private Repository repository;
    public AllProductsModel(Context context){
        this.repository = new Repository(context);
    }

    public Cursor getAllProducts(){
        return repository.getAllProducts();
    }
}
