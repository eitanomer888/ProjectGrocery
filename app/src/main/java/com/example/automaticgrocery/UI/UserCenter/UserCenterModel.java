package com.example.automaticgrocery.UI.UserCenter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    public void showUpdateDialog() {
        Dialog dialog = new Dialog(repository.getContext());

        dialog.setContentView(R.layout.user_update_dialog);

        Button btnUpDiCancle = dialog.findViewById(R.id.btnUpDiCancle);
        Button btnUpDiUpdate = dialog.findViewById(R.id.btnUpDiUpdate);

        EditText etUpDiUserName = dialog.findViewById(R.id.etUpDiUserName);
        EditText etUpDiUserPass = dialog.findViewById(R.id.etUpDiUserPass);



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




                Toast.makeText(repository.getContext(), "updated successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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
}
