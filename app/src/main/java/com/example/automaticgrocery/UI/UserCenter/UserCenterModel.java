package com.example.automaticgrocery.UI.UserCenter;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.BroadcastReceiver.AlarmReceiver;
import com.example.automaticgrocery.data.DB.FireBaseHelper;
import com.example.automaticgrocery.data.Items.CurrentUser;
import com.example.automaticgrocery.data.Repository.Repository;

public class UserCenterModel
{
    //repository instance for communication
    private Repository repository;

    //context instance for functions
    private Context context;

    //Constructor
    public UserCenterModel(Context context){
        repository = new Repository(context);
        this.context = context;
    }

    //data confirm
    public void DataConfirm(String username,String password, FireBaseHelper.ScanComplete callback){repository.DataConfirm(username,password,callback);}

    //delete user
    public void DeleteUser(FireBaseHelper.DeleteComplete callback){repository.DeleteUser(callback);}

    //update user
    public void UpdateUser(String fireId,String username, String password, FireBaseHelper.UpdateComplete callback){repository.UpdateUser(fireId,username, password, callback);}



    //sharedPreference actions//
    public void WriteStringToSharedPreferences(String key,String value)
    {
        repository.WriteStringToSharedPreferences(key,value);
    }

    public String ReadStringFromSharedPreferences(String key,String defaultValue)
    {
        return repository.ReadStringFromSharedPreferences(key, defaultValue);
    }

    public void WriteBooleanToSharedPreferences(String key,boolean value)
    {
        repository.WriteBooleanToSharedPreferences(key, value);
    }

    public void clear_sharedPreference(){
        WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
        WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
        WriteStringToSharedPreferences(String.valueOf(R.string.user_fireId_key),"");
        WriteBooleanToSharedPreferences(String.valueOf(R.string.user_loggedIn_key),false);
    }

    //##################//

    //getter and setters//
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //############//

    //update dialog//
    public void showUpdateDialog(TextView tvName)
    {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.user_update_dialog);

        Button btnUpDiCancle = dialog.findViewById(R.id.btnUpDiCancle);
        Button btnUpDiUpdate = dialog.findViewById(R.id.btnUpDiUpdate);

        EditText etUpDiUserName = dialog.findViewById(R.id.etUpDiUserName);
        EditText etUpDiUserPass = dialog.findViewById(R.id.etUpDiUserPass);

        etUpDiUserName.setText(ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),""));
        etUpDiUserPass.setText(ReadStringFromSharedPreferences(String.valueOf(R.string.user_password_key),""));

        //close dialog without updating
        btnUpDiCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //update user
        btnUpDiUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String currnet_name = CurrentUser.getUsername();
                String name = etUpDiUserName.getText().toString().trim();
                String pass = etUpDiUserPass.getText().toString().trim();

                //check user input valid data
                if (!updateValidation(name,pass)){
                    //SENDING TOAST MESSAGE IN FUNCTION OF VALIDATION ALREADY
                }
                //check if user changed name
                else if(currnet_name.equals(name))
                {
                    //update user
                    UpdateUser(CurrentUser.getFireId(), name, pass, new FireBaseHelper.UpdateComplete() {
                        @Override
                        public void onUpdateComplete(boolean flag) {
                            if (flag){
                                WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                                dialog.dismiss();
                            }

                        }
                    });
                }
                //if user changed name
                else
                {
                    //check if username is already taken
                    DataConfirm(name, pass, new FireBaseHelper.ScanComplete() {
                        @Override
                        public void onScanComplete(boolean flag) {
                            if (flag)
                            {
                                //update user
                                UpdateUser(CurrentUser.getFireId(), name, pass, new FireBaseHelper.UpdateComplete() {
                                    @Override
                                    public void onUpdateComplete(boolean flag) {
                                        if (flag){
                                            WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),name);
                                            WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                                            tvName.setText(name);
                                            if(name.equals(repository.getContext().getString(R.string.admin)))
                                                tvName.setText("Admin");

                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(repository.getContext(), "username is already taken", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        dialog.show();
    }

    //check input
    public boolean updateValidation(String name,String pass){
        if(CurrentUser.getUsername().equals("") || name.equals("") || pass.equals("")){
            Toast.makeText(repository.getContext(), "pls fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pass.length() < 4 || pass.length() > 12){
            Toast.makeText(context, "password length between 4 to 12", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //cancel alarm
    public void cancelAlarm() {
        repository.cancelAlarm();
    }

    //remove all products
    public void DeleteAllProducts(){
        repository.DeleteAllProducts();
    }

    //add product
    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category,int target_amount,int last_date_amount){
        repository.addProduct(internal_reference,name,barcode,amount,fill_date,last_date,category,target_amount,last_date_amount);
    }
}
