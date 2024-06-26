package com.example.automaticgrocery.UI.AllProducts;

import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.UI.MyRecycleView.MyAllAdapter;
import com.example.automaticgrocery.data.Items.AllItem;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AllProductsModel {

    //repository instance for communication
    private Repository repository;

    //Constructor
    public AllProductsModel(Context context){
        this.repository = new Repository(context);
    }

    //get products by category selected and search string
    public Cursor getProductsByCategoryAndString(String category, String str){return repository.getProductsByCategoryAndString(category,str);}


    //initialize recycle view
    public  void InitializeRecycleView(String category, String str , RecyclerView recyclerView , TextView etSearchProduct){
        List<AllItem> items = new ArrayList<>();

        Cursor cursor;
        cursor = getProductsByCategoryAndString(category, str);

        cursor.moveToFirst();
        int l = cursor.getCount();
        for (int i = 0; i < l; i++)
        {
            items.add(new AllItem(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getInt(8)));
            cursor.moveToNext();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(repository.getContext()));
        recyclerView.setAdapter(new MyAllAdapter(recyclerView.getContext(),items,etSearchProduct));
    }
}
