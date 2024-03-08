package com.example.automaticgrocery.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.UI.ExpiredFragment.ExpiredFragment;
import com.example.automaticgrocery.UI.FillFragment.FillFragment;
import com.example.automaticgrocery.UI.UserCenter.UserCenter;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Login.LoginPage;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainModel mainModel;
    private FragmentManager fm;

    private ImageView userIcon,barcodeIcon;

    private Spinner spI;
    private Button btnSw;

    private TextView tvSelectedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainModel = new MainModel(this);

        userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(this);

        barcodeIcon = findViewById(R.id.barcodeIcon);
        barcodeIcon.setOnClickListener(this);

        btnSw = findViewById(R.id.btnSw);
        btnSw.setOnClickListener(this);

        tvSelectedClass = findViewById(R.id.tvSelectedClass);


        spI = findViewById(R.id.spI);
        List<String> lst = new LinkedList<>();
        lst.add("הכל");
        lst.add("חטיפים, מתוקים ודגני בוקר");
        lst.add("מזון מקורר, קפוא ונקניקים");
        lst.add("מוצרי חלב וביצים");

//        Set<String> set = new HashSet<>();
//        Cursor cursor = mainModel.getAllProducts();
//        cursor.moveToFirst();
//        int l = cursor.getCount();
//        for (int i = 0; i < l; i++) {
//            set.add(cursor.getString(7));
//            cursor.moveToNext();
//        }
//////        Object[] arrayItem = set.toArray();
//////        for (int i = 0; i < arrayItem.length; i++)
//////        {
//////            lst.add(arrayItem[i].toString());
//////        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spI.setAdapter(adapter);
        spI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = spI.getItemAtPosition(position).toString();
                tvSelectedClass.setText(category);

                Cursor cursor = mainModel.getProductsByCategory(category);
                cursor.moveToFirst();
                int l = cursor.getCount();
                for (int i = 0; i < l; i++)
                {
                    Toast.makeText(MainActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    cursor.moveToNext();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.contentFragment,FillFragment.class,null).commit();



        if(!mainModel.ReadBooleanFromSharedPreferences(String.valueOf(R.string.user_remember_key),false))
        {
            Intent i = new Intent(MainActivity.this, LoginPage.class);
            startActivity(i);
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!mainModel.ReadBooleanFromSharedPreferences(String.valueOf(R.string.user_remember_key),false))
        {
            Intent i = new Intent(MainActivity.this, LoginPage.class);
            startActivity(i);
        }
    }


    @Override
    public void onClick(View view) {
        if(btnSw == view){
            if (MainModel.isFill){
                fm.beginTransaction().replace(R.id.contentFragment,ExpiredFragment.class,null).commit();
                btnSw.setText(R.string.exSW);
                MainModel.isFill = false;
                spI.setSelection(0);
            }
            else{
                fm.beginTransaction().replace(R.id.contentFragment,FillFragment.class,null).commit();
                btnSw.setText(R.string.fillSW);
                MainModel.isFill = true;
            }
        }
        else if(userIcon == view)
        {
            //move to userHub
            Intent i = new Intent(MainActivity.this, UserCenter.class);
            startActivity(i);
        }
        else if(barcodeIcon == view)
        {
            //adding the products for now...
            mainModel.DeleteAllProducts();

            mainModel.addProduct("7290000066318","חטיף במבה 80 גרם (אסם)","7290000066318", 77,"08/03/24" ,"08/03/24", "הכל / חטיפים, מתוקים ודגני בוקר / חטיפים מלוחים / חטיפי בוטנים");
            mainModel.addProduct("7290006272072","חטיפי פיצה, 650 גרם (מעדנות)", "7290006272072", 39, "08/03/24" ,"08/03/24","הכל / מזון מקורר, קפוא ונקניקים / מוצרי בצק ומאפה קפוא / פיצות ומאפה איטלקי");
            mainModel.addProduct("7290004131074", "חלב 3%, 1 ליטר (תנובה)", "72900041310740", 505, "08/03/24" ,"08/03/24", "הכל / מוצרי חלב וביצים / ריק / חלב טרי");
            mainModel.addProduct("7290010471669","יוגורט בטעם תות עם קורנפלקס מצופה שוקולד חלב, 175 גרם (דנונה)", "7290010471669", 23,"08/03/24" ,"08/03/24","הכל / מוצרי חלב וביצים / מעדנים וקינוחים / מעדני חלב" );
        }



    }
}