package com.example.automaticgrocery.UI.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.Manifest;

import com.example.automaticgrocery.UI.AllProducts.AllProducts;
import com.example.automaticgrocery.UI.ExpiredFragment.ExpiredFragment;
import com.example.automaticgrocery.UI.FillFragment.FillFragment;
import com.example.automaticgrocery.UI.UserCenter.UserCenter;
import com.example.automaticgrocery.data.BroadcastReceiver.AlarmReceiver;
import com.example.automaticgrocery.data.DB.MyDatabaseHelper;
import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Login.LoginPage;
import com.example.automaticgrocery.data.Items.CurrentUser;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //static values
    private static final String PERMISSION_NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS;
    private static final int NOTIFICATIONS_REQ_CODE = 100;
    //model instance
    private MainModel mainModel;

    //UI components
    private FragmentManager fm;

    private ImageView userIcon,barcodeIcon;

    private Spinner spI;
    private Button btnSw;

    private TextView tvSelectedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize objects
        mainModel = new MainModel(this);

        if (mainModel.ReadBooleanFromSharedPreferences(String.valueOf(R.string.first_time_key),true))
        {
            mainModel.WriteBooleanToSharedPreferences(String.valueOf(R.string.first_time_key),false);

            //adding the products
            mainModel.DeleteAllProducts();

            mainModel.addProduct("7290000066318","חטיף במבה 80 גרם (אסם)","7290000066318", 77,"08/03/24" ,"08/03/24", "הכל / חטיפים, מתוקים ודגני בוקר / חטיפים מלוחים / חטיפי בוטנים",100,20);
            mainModel.addProduct("7290006272072","חטיפי פיצה, 650 גרם (מעדנות)", "7290006272072", 39, "08/03/24" ,"08/03/24","הכל / מזון מקורר, קפוא ונקניקים / מוצרי בצק ומאפה קפוא / פיצות ומאפה איטלקי",50,7);
            mainModel.addProduct("7290004131074", "חלב 3%, 1 ליטר (תנובה)", "72900041310740", 505, "08/03/24" ,"08/03/24", "הכל / מוצרי חלב וביצים / ריק / חלב טרי",550,100);
            mainModel.addProduct("7290010471669","יוגורט בטעם תות עם קורנפלקס מצופה שוקולד חלב, 175 גרם (דנונה)", "7290010471669", 23,"08/03/24" ,"08/03/24","הכל / מוצרי חלב וביצים / מעדנים וקינוחים / מעדני חלב" ,80,4);

        }

        userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(this);

        barcodeIcon = findViewById(R.id.barcodeIcon);
        barcodeIcon.setOnClickListener(this);

        btnSw = findViewById(R.id.btnSw);
        btnSw.setOnClickListener(this);

        tvSelectedClass = findViewById(R.id.tvSelectedClass);

        //initialize spinner
        spI = findViewById(R.id.spI);
        List<String> lst = new LinkedList<>();
        lst.add("הכל");
        lst.add("חטיפים, מתוקים ודגני בוקר");
        lst.add("מזון מקורר, קפוא ונקניקים");
        lst.add("מוצרי חלב וביצים");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spI.setAdapter(adapter);
        spI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = spI.getItemAtPosition(position).toString();

                tvSelectedClass.setText(category);
                mainModel.setCurrent_category(category);

                //communicate with fragments
                if(MainModel.isFill){
                    FillFragment fillFragment = new FillFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fillFragment).commit();
                }
                else{
                    ExpiredFragment expiredFragment = new ExpiredFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,expiredFragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //initialize fragment
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.contentFragment,FillFragment.class,null).commit();



        //permission actions
        requestRuntimePermission();

        //notification+alarm action
        try {
            mainModel.scheduleAlarm();
        }
        catch (Exception e){
            Log.e("MainActivity", e + "");
        }

    }


    //Runtime permission
    public void requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this,PERMISSION_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(this, "Can use Notifications!", Toast.LENGTH_SHORT).show();
        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_NOTIFICATIONS)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This app requires NOTIFICATION permission in order to send important updates about the products")
                    .setTitle("Permission Required")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{PERMISSION_NOTIFICATIONS},NOTIFICATIONS_REQ_CODE);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            builder.show();

        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{PERMISSION_NOTIFICATIONS},NOTIFICATIONS_REQ_CODE);
        }
    }

    //runtime permission (after result)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == NOTIFICATIONS_REQ_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Toast.makeText(this, "Can use Notifications!", Toast.LENGTH_SHORT).show();
            }
            else if (!ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSION_NOTIFICATIONS)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Notifications is unavailable because you denied the notification permission. Please allow permission from settings to get this feature.")
                        .setTitle("Permission Required")
                        .setCancelable(false)
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",getPackageName(),null);
                                intent.setData(uri);
                                startActivity(intent);

                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
            }
            else{
                requestRuntimePermission();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //check if user is logged in
        if(!mainModel.ReadBooleanFromSharedPreferences(String.valueOf(R.string.user_loggedIn_key),false))
        {
            Intent i = new Intent(MainActivity.this, LoginPage.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MainModel.isFill){
            FillFragment fillFragment = new FillFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fillFragment).commit();
        }
        else{
            ExpiredFragment expiredFragment = new ExpiredFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,expiredFragment).commit();
        }
    }

    @Override
    public void onClick(View view)
    {
        if(btnSw == view){
            //move between fragments and set the right taskbar
            if (MainModel.isFill)
            {
                fm.beginTransaction().replace(R.id.contentFragment,ExpiredFragment.class,null).commit();
                btnSw.setText(R.string.exSW);
                MainModel.isFill = false;
                spI.setSelection(0);
            }
            else{
                fm.beginTransaction().replace(R.id.contentFragment,FillFragment.class,null).commit();
                btnSw.setText(R.string.fillSW);
                MainModel.isFill = true;
                spI.setSelection(0);
            }
        }
        else if(userIcon == view)
        {
            //move to userHub
            Intent i = new Intent(MainActivity.this, UserCenter.class);
            startActivity(i);
        }
        else if(barcodeIcon == view)
        {
            //communicate with fragments
            if(MainModel.isFill){
                FillFragment fillFragment = new FillFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fillFragment).commit();
            }
            else{
                ExpiredFragment expiredFragment = new ExpiredFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,expiredFragment).commit();
            }

            Intent intent = new Intent(MainActivity.this, AllProducts.class);
            startActivity(intent);
        }



    }
}