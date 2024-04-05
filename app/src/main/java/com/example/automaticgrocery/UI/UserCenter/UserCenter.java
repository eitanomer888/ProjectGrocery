package com.example.automaticgrocery.UI.UserCenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.R;

public class UserCenter extends AppCompatActivity implements View.OnClickListener {

    private UserCenterModel userCenterModel;
    private Button btnUpdateUser,btnDeleteUser,btnLogoutUser;
    private TextView tvName;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        userCenterModel = new UserCenterModel(this);


        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        tvName = findViewById(R.id.tvName);
        String Uname = userCenterModel.ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),"");
        tvName.setText(Uname);
        if(Uname.equals(getString(R.string.admin)))
            tvName.setText("Admin");




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
            userCenterModel.showUpdateDialog(tvName);
        }
        else if(btnDeleteUser == v){
            //delete user
            userCenterModel.deleteOneRowUser(userCenterModel.ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),""));

            userCenterModel.clear_sharedPreference();

            finish();
        }
        else if(btnLogoutUser == v)
        {
           //logout user

           //clear sharedPreference
            userCenterModel.clear_sharedPreference();
           //move to main
            finish();
        }
        else if(v == backButton){
            finish();
        }
    }
}