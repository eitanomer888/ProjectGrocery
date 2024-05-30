package com.example.automaticgrocery.UI.AllProducts;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.ExpiredFragment.ExpiredFragment;
import com.example.automaticgrocery.UI.FillFragment.FillFragment;
import com.example.automaticgrocery.UI.Main.MainModel;
import com.example.automaticgrocery.UI.MyRecycleView.MyAdapter;
import com.example.automaticgrocery.UI.MyRecycleView.MyAllAdapter;
import com.example.automaticgrocery.data.Items.AllItem;
import com.example.automaticgrocery.data.Items.ExpiredItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllProducts extends AppCompatActivity implements View.OnClickListener {

    private AllProductsModel allProductsModel;
    private ImageView btnReturn;

    private Spinner spiAllProducts;
    private EditText etSearchProduct;
    private RecyclerView recycleViewAll;

    private String category,strSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            allProductsModel = new AllProductsModel(this);
            strSearch = "";
            category = "הכל";

            btnReturn = findViewById(R.id.btnReturn);
            btnReturn.setOnClickListener(this);
            spiAllProducts = findViewById(R.id.spiAllProducts);
            etSearchProduct = findViewById(R.id.etSearchProduct);
            recycleViewAll = findViewById(R.id.recycleViewAll);

            //spinner for choosing categories
            List<String> lst = new LinkedList<>();
            lst.add("הכל");
            lst.add("חטיפים, מתוקים ודגני בוקר");
            lst.add("מזון מקורר, קפוא ונקניקים");
            lst.add("מוצרי חלב וביצים");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiAllProducts.setAdapter(adapter);
            spiAllProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category = spiAllProducts.getItemAtPosition(position).toString();
                    allProductsModel.InitializeRecycleView(category,strSearch,recycleViewAll,etSearchProduct);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            //text watcher for enabling search by text
            etSearchProduct.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    strSearch = etSearchProduct.getText().toString().trim();
                    allProductsModel.InitializeRecycleView(category,strSearch,recycleViewAll,etSearchProduct);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });



            //initialize recycle view
            allProductsModel.InitializeRecycleView(category,strSearch,recycleViewAll,etSearchProduct);


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