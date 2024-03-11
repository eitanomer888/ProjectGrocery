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
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.SignUp.SignUpPage;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView forgotPass, signUp;
    private EditText userPass, userName;
    private CheckBox cbRemeber;

    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginModel = new LoginModel(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        userPass = findViewById(R.id.userPass);

        userName = findViewById(R.id.userName);

        forgotPass = findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(this);

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        cbRemeber = findViewById(R.id.cbRemeber);

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
                if(loginModel.searchUser(name,pass))
                {
                    if(cbRemeber.isChecked())
                    {
                        loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),name);
                        loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                        loginModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_remember_key),true);
                    }
                    else{
                        loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
                        loginModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
                        loginModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_remember_key),false);
                    }
                    Toast.makeText(this, "logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(this, "user is not found", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else if(v == forgotPass)
        {
            //send ForgotPassword page
            Toast.makeText(this, "send ForgotPassword page", Toast.LENGTH_SHORT).show();

        }
        else if(v == signUp)
        {
            //send to signUp page
            Intent i = new Intent(LoginPage.this, SignUpPage.class);
            startActivity(i);
        }
    }
}