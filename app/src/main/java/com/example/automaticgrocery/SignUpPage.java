package com.example.automaticgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private EditText SUuserName,SUuserPass;
    private MyDatabaseHelper myDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        myDatabaseHelper = new MyDatabaseHelper(this);

        btnSignUp = findViewById(R.id.btnSignUp);
        SUuserName = findViewById(R.id.SUuserName);
        SUuserPass = findViewById(R.id.SUuserPass);


        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnSignUp)
        {
            String name , pass;
            name = SUuserName.getText().toString();
            pass = SUuserPass.getText().toString();
            if(!(pass.equals("") || name.equals("")))
            {
                //need to add check of ensuring that there is not identical user exist

                myDatabaseHelper.addUser(name,pass);
            }
            else{
                Toast.makeText(this, "pls fill all fields", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}