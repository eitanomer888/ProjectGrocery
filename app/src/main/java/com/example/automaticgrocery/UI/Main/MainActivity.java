package com.example.automaticgrocery.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.automaticgrocery.UI.ExpiredFragment.ExpiredFragment;
import com.example.automaticgrocery.UI.FillFragment.FillFragment;
import com.example.automaticgrocery.UI.UserCenter.UserCenter;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Login.LoginPage;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainModel mainModel;
    private FragmentManager fm;

    private ImageView userIcon,barcodeIcon;

    private Spinner spI;
    private Button btnSw;

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

        spI = findViewById(R.id.spI);
        List<String> lst = new LinkedList<>();
        lst.add("one");
        lst.add("two");
        lst.add("three");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spI.setAdapter(adapter);

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

        }



    }
}