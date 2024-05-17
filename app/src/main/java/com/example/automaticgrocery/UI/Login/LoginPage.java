package com.example.automaticgrocery.UI.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.SignUp.SignUpPage;
import com.example.automaticgrocery.data.Items.CurrentUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView signUp;
    private EditText userPass, userName;

    private LoginModel loginModel;

    private CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginModel = new LoginModel(this);
        currentUser = new CurrentUser();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        userPass = findViewById(R.id.userPass);

        userName = findViewById(R.id.userName);

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin)
        {
            //send to the main page with the planogram

            //get user name + password
            String name , pass;
            name = userName.getText().toString().trim();
            pass = userPass.getText().toString().trim();

            if(loginModel.loginValidation(name,pass))
            {
                loginModel.LoginConfirm(name, pass, new FireBaseHelper.SearchComplete() {
                    @Override
                    public void onSearchComplete(Boolean flag,String id) {
                        if(flag){
                            loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),name);
                            loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                            loginModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_loggedIn_key),true);
                            currentUser.InitializeUser(name,pass,id);
                            Toast.makeText(loginModel.context, "logged in successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(loginModel.context, "user is not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        else if(v == signUp)
        {
            //send to signUp page
            Intent i = new Intent(LoginPage.this, SignUpPage.class);
            startActivity(i);
        }
    }
}