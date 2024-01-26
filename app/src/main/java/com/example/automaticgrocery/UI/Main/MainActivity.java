package com.example.automaticgrocery.UI.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Login.LoginPage;

public class MainActivity extends AppCompatActivity {

    private MainModel mainModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainModel = new MainModel(this);

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
}