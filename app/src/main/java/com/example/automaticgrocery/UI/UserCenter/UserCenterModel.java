package com.example.automaticgrocery.UI.UserCenter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.Repository.Repository;

public class UserCenterModel
{
    private Repository repository;
    public UserCenterModel(Context context){
        repository = new Repository(context);
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
                    Toast.makeText(repository.getContext(), "pls fill all fields/saved data failed", Toast.LENGTH_SHORT).show();
                else if(currnet_name.equals(name))
                {
                    //if user didn't change name
                    boolean up = repository.updateUserData(currnet_name,name,pass);
                    if(up)
                    {
                        WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                    }

                    dialog.dismiss();
                }
                else
                {
                    //if user changed name
                    if(isNameTaken(name,currnet_name))
                        Toast.makeText(repository.getContext(), "username is already taken", Toast.LENGTH_SHORT).show();
                    else
                    {
                        boolean up = repository.updateUserData(currnet_name,name,pass);
                        if(up)
                        {
                            WriteStringToSharedPreferences(String.valueOf(R.string.user_name_key),name);
                            WriteStringToSharedPreferences(String.valueOf(R.string.user_password_key),pass);
                            tvName.setText(name);
                        }

                        dialog.dismiss();
                    }

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
}
