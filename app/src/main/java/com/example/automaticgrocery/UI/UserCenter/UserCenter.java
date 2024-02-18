package com.example.automaticgrocery.UI.UserCenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.automaticgrocery.R;

public class UserCenter extends AppCompatActivity implements View.OnClickListener {

    private UserCenterModel userCenterModel;
    private Button btnUpdateUser,btnDeleteUser,btnLogoutUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        userCenterModel = new UserCenterModel(this);

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
    }
}