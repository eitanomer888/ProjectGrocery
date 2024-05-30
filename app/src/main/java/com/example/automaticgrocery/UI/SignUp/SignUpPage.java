package com.example.automaticgrocery.UI.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.automaticgrocery.UI.Login.LoginModel;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.Items.CurrentUser;

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
            //check if user input is valid
            if(signUpModel.sighUpValidation(name,pass))
            {
                //check if username is already exist
                signUpModel.SignUpConfirm(name, pass, new FireBaseHelper.ScanComplete() {
                    @Override
                    public void onScanComplete(boolean flag) {
                        //add user to firebase
                        if (flag){
                            signUpModel.AddUser(name, pass, new FireBaseHelper.AddComplete() {
                                @Override
                                public void onAddComplete(boolean flag, String id) {
                                    if (flag)
                                    {
                                        //user added successfully
                                        signUpModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),name);
                                        signUpModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                                        signUpModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_fireId_key),id);
                                        signUpModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_loggedIn_key),true);
                                        CurrentUser.InitializeUser(name,pass,id);
                                        Toast.makeText(signUpModel.getContext(), "logged in successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpPage.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(signUpModel.getContext(), "user is already exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}