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

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainModel mainModel;
    private FragmentManager fm;

    private ImageView userIcon;

    private Spinner spI;
    private Button btnSw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainModel = new MainModel(this);

        userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(this);


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




        Intent i = new Intent(MainActivity.this, LoginPage.class);
        startActivity(i);

    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.custom_fill_dialog);

        Button btnD_cancel = dialog.findViewById(R.id.btnD_cancel);
        Button btnD_update = dialog.findViewById(R.id.btnD_update);
        EditText et = dialog.findViewById(R.id.etD_ProductName);



        btnD_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,et.getText().toString() , Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnD_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "לא עודכן", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
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



    }
}