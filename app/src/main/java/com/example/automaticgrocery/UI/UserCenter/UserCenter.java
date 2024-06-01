package com.example.automaticgrocery.UI.UserCenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.Items.CurrentUser;

public class UserCenter extends AppCompatActivity implements View.OnClickListener {

    //model instance
    private UserCenterModel userCenterModel;

    //UI elements
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
        String Uname = CurrentUser.getUsername();
        tvName.setText(Uname);
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        if(Uname.equals(getString(R.string.admin)))
        {
            Button btnAddProducts = new Button(this);
            btnAddProducts.setText("Refresh Products");
            btnAddProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //adding the products
                    userCenterModel.DeleteAllProducts();

                    userCenterModel.addProduct("7290000066318","חטיף במבה 80 גרם (אסם)","7290000066318", 77,"08/03/24" ,"08/03/24", "הכל / חטיפים, מתוקים ודגני בוקר / חטיפים מלוחים / חטיפי בוטנים",100,20);
                    userCenterModel.addProduct("7290006272072","חטיפי פיצה, 650 גרם (מעדנות)", "7290006272072", 39, "08/03/24" ,"08/03/24","הכל / מזון מקורר, קפוא ונקניקים / מוצרי בצק ומאפה קפוא / פיצות ומאפה איטלקי",50,7);
                    userCenterModel.addProduct("7290004131074", "חלב 3%, 1 ליטר (תנובה)", "72900041310740", 505, "08/03/24" ,"08/03/24", "הכל / מוצרי חלב וביצים / ריק / חלב טרי",550,100);
                    userCenterModel.addProduct("7290010471669","יוגורט בטעם תות עם קורנפלקס מצופה שוקולד חלב, 175 גרם (דנונה)", "7290010471669", 23,"08/03/24" ,"08/03/24","הכל / מוצרי חלב וביצים / מעדנים וקינוחים / מעדני חלב" ,80,4);

                }
            });

            relativeLayout.addView(btnAddProducts);
        }





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
            userCenterModel.DeleteUser(new FireBaseHelper.DeleteComplete() {
                @Override
                public void onDeleteComplete(boolean flag) {
                    if (flag){
                        Toast.makeText(userCenterModel.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        userCenterModel.clear_sharedPreference();
                        CurrentUser.ClearUser();
                        userCenterModel.cancelAlarm();
                        finish();
                    }
                    else{
                        Toast.makeText(userCenterModel.getContext(), "Failed to Delete", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(btnLogoutUser == v)
        {
           //logout user
            userCenterModel.cancelAlarm();

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