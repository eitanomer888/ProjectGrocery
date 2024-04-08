package com.example.automaticgrocery.UI.AllProducts;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.MyRecycleView.MyAdapter;
import com.example.automaticgrocery.UI.MyRecycleView.MyAllAdapter;
import com.example.automaticgrocery.data.Items.AllItem;
import com.example.automaticgrocery.data.Items.ExpiredItem;

import java.util.ArrayList;
import java.util.List;

public class AllProducts extends AppCompatActivity implements View.OnClickListener {

    private AllProductsModel allProductsModel;
    private ImageView btnReturn;
    private EditText etSearchProduct;
    private RecyclerView recycleViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            allProductsModel = new AllProductsModel(this);

            btnReturn = findViewById(R.id.btnReturn);
            btnReturn.setOnClickListener(this);
            etSearchProduct = findViewById(R.id.etSearchProduct);
            recycleViewAll = findViewById(R.id.recycleViewAll);


            List<AllItem> items = new ArrayList<>();

            Cursor cursor = allProductsModel.getAllProducts();
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

            recycleViewAll.setLayoutManager(new LinearLayoutManager(this));
            recycleViewAll.setAdapter(new MyAllAdapter(this,items));


            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnReturn){
            finish();
        }
    }
}