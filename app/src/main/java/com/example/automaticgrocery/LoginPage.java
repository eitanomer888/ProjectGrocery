package com.example.automaticgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView tvUsername, tvPass, forgotPass, signUp;
    private EditText userPass, userName;

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btnLogin = findViewById(R.id.btnLogin);
        tvUsername = findViewById(R.id.tvUsername);
        tvPass = findViewById(R.id.tvPass);
        userPass = findViewById(R.id.userPass);
        userName = findViewById(R.id.userName);
        forgotPass = findViewById(R.id.forgotPass);
        signUp = findViewById(R.id.signUp);

        myDatabaseHelper = new MyDatabaseHelper(this);

        signUp.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin)
        {
            //send to the main page with the planogram
            String name , pass;
            name = userName.getText().toString();
            pass = userPass.getText().toString();
            if(!(pass.equals("") || name.equals("")))
            {
                Cursor cursor = myDatabaseHelper.readAllData();
                if(cursor != null)
                {

                    cursor.moveToFirst();
                    boolean flag = false;
                    int a = cursor.getCount();
                    String n, p;
                    for (int i = 0; i < a; i++)
                    {
                        n = cursor.getString(1);
                        p = cursor.getString(2);

                        if(n.equals(name) && p.equals(pass)){
                            flag = true;
                        }
                    }
                    if(flag)
                    {
                        //signed in
                        Toast.makeText(this, "user found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "wrong user name/password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else{
                Toast.makeText(this, "pls fill all fields", Toast.LENGTH_SHORT).show();
            }

        }
        else if(v == forgotPass)
        {
            //send ForgotPassword page

        }
        else if(v == signUp)
        {
            //send to signUp page
            Intent i = new Intent(LoginPage.this, SignUpPage.class);
            startActivity(i);
        }
    }
}