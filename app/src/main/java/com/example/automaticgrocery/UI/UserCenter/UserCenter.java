package com.example.automaticgrocery.UI.UserCenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.automaticgrocery.R;

public class UserCenter extends AppCompatActivity implements View.OnClickListener {

    private UserCenterModel userCenterModel;
    private Button btnUpdateUser,btnDeleteUser,btnLogoutUser;

    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        userCenterModel = new UserCenterModel(this);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnLogoutUser = findViewById(R.id.btnLogoutUser);

        btnUpdateUser.setOnClickListener(this);
        btnDeleteUser.setOnClickListener(this);
        btnLogoutUser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        if(btnUpdateUser == v)
        {
            userCenterModel.showUpdateDialog();
        }
        else if(btnDeleteUser == v){
            //delete user
            userCenterModel.deleteOneRowUser(userCenterModel.ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),""));

            userCenterModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
            userCenterModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
            userCenterModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_remember_key),false);

            finish();
        }
        else if(btnLogoutUser == v)
        {
           //logout user

           //clear sheareprefernce
            userCenterModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
            userCenterModel.WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
            userCenterModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.user_remember_key),false);
           //move to main
            finish();
        }
        else if(v == backButton){
            finish();
        }
    }
}