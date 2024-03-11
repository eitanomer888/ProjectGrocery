package com.example.automaticgrocery.UI.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.automaticgrocery.UI.Login.LoginModel;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private EditText SUuserName,SUuserPass;
    private SignUpModel signUpModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        signUpModel = new SignUpModel(this);

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
            name = SUuserName.getText().toString().trim();
            pass = SUuserPass.getText().toString().trim();
            if(signUpModel.sighUpValidation(name,pass))
            {
                if(signUpModel.NoDuplicate(name))
                {
                    signUpModel.addUser(name,pass);
                    finish();
                }
                else{
                    Toast.makeText(this, "user name is already taken", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "pls fill all fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
}