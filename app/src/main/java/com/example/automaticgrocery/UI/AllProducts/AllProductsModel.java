package com.example.automaticgrocery.UI.AllProducts;

import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.UI.MyRecycleView.MyAllAdapter;
import com.example.automaticgrocery.data.Items.AllItem;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AllProductsModel {
    private Repository repository;
    public AllProductsModel(Context context){
        this.repository = new Repository(context);
    }

    public Cursor getAllProducts(){
        return repository.getAllProducts();
    }

    public Cursor getProductsByCategory(String category){
        return repository.getProductsByCategory(category);
    }

    public Cursor getProductsByCategoryAndString(String category, String str){return repository.getProductsByCategoryAndString(category,str);}

    public  void InitializeRecycleView(String category, String str ,RecyclerView recyclerView){
        List<AllItem> items = new ArrayList<>();


        Cursor cursor;
        if(str.equals(""))
            cursor = getProductsByCategory(category);
        else
            cursor = getProductsByCategoryAndString(category, str);

        cursor.moveToFirst();
        int l = cursor.getCount();
        for (int i = 0; i < l; i++)
        {
            //להוסיף בדיקה שהמוצר אכן לא בתוקף
            items.add(new AllItem(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getInt(8)));
            cursor.moveToNext();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(repository.getContext()));
        recyclerView.setAdapter(new MyAllAdapter(recyclerView.getContext(),items));
    }
}
