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
    private Repository repository;
    private Context context;
    public UserCenterModel(Context context){
        repository = new Repository(context);
        this.context = context;
    }

    public void DataConfirm(String username,String password, FireBaseHelper.ScanComplete callback){repository.DataConfirm(username,password,callback);}

    public void DeleteUser(FireBaseHelper.DeleteComplete callback){repository.DeleteUser(callback);}

    public void UpdateUser(String fireId,String username, String password, FireBaseHelper.UpdateComplete callback){repository.UpdateUser(fireId,username, password, callback);}



    public void clear_sharedPreference(){
        WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),"");
        WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),"");
        WriteStringToSharedPreferences(String.valueOf(R.string.user_fireId_key),"");
        WriteBooleanToSharedPreferences(String.valueOf(R.string.user_loggedIn_key),false);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void showUpdateDialog(TextView tvName) {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.user_update_dialog);

        Button btnUpDiCancle = dialog.findViewById(R.id.btnUpDiCancle);
        Button btnUpDiUpdate = dialog.findViewById(R.id.btnUpDiUpdate);

        EditText etUpDiUserName = dialog.findViewById(R.id.etUpDiUserName);
        EditText etUpDiUserPass = dialog.findViewById(R.id.etUpDiUserPass);

        etUpDiUserName.setText(ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),""));
        etUpDiUserPass.setText(ReadStringFromSharedPreferences(String.valueOf(R.string.user_password_key),""));

        btnUpDiCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpDiUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String currnet_name = ReadStringFromSharedPreferences(String.valueOf(R.string.user_name_key),"");
                String name = etUpDiUserName.getText().toString().trim();
                String pass = etUpDiUserPass.getText().toString().trim();

                if(currnet_name.equals("") || name.equals("") || pass.equals(""))
                    Toast.makeText(repository.getContext(), "pls fill all fields", Toast.LENGTH_SHORT).show();
                else if (pass.length() < 4 || pass.length() > 12)
                Toast.makeText(context, "password length between 4 to 12", Toast.LENGTH_SHORT).show();
                else if(currnet_name.equals(name))
                {
                    //if user didn't change name
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
                else
                {
                    //if user changed name
                    DataConfirm(name, pass, new FireBaseHelper.ScanComplete() {
                        @Override
                        public void onScanComplete(boolean flag) {
                            if (flag)
                            {
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

    public boolean ReadBooleanFromSharedPreferences(String key,boolean defaultValue)
    {
        return repository.ReadBooleanFromSharedPreferences(key, defaultValue);
    }

    public void deleteOneRowUser(String username){repository.deleteOneRowUser(username);}

    public boolean isNameTaken(String name,String currnet_name)
    {
        Cursor cursor = repository.readData();
        if(cursor == null)
            return false;

        cursor.moveToFirst();
        int l = cursor.getCount();
        for (int i = 0; i < l; i++)
        {
            if(cursor.getString(0).equals(name) && !currnet_name.equals(name))
                return true;

            cursor.moveToNext();
        }

        return false;
    }

    public void cancelAlarm() {
        repository.cancelAlarm();
    }


}
