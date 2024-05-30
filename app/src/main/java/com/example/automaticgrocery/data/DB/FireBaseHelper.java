package com.example.automaticgrocery.data.DB;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.automaticgrocery.data.Items.CurrentUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FireBaseHelper {
    FirebaseFirestore db;
    private Context context;

    public FireBaseHelper(Context context) {
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }


    public interface UsersRetrievedListener
    {
        void onUsersRetrieved(Task<QuerySnapshot> task,boolean isUserFound,String id);
    }

    //Read users data from firebase
    public void ReadUsers(UsersRetrievedListener callback){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            callback.onUsersRetrieved(task,false,"");
                        }
                        else
                        {
                            callback.onUsersRetrieved(null,false,"");
                        }
                    }
                });
    }

    public interface SearchComplete
    {
        void onSearchComplete(Boolean flag,String fireId);
    }

    //login confirm
    public void LoginConfirm(String username,String password, SearchComplete callback)
    {
        if (username != null && password != null){
            ReadUsers(new UsersRetrievedListener() {
                @Override
                public void onUsersRetrieved(Task<QuerySnapshot> task, boolean isUserFound,String id) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getData().get("username").toString();
                        String pass = document.getData().get("password").toString();
                        if(username.equals(name) && password.equals(pass)){
                            isUserFound = true;
                            id = document.getId();
                        }
                    }
                    callback.onSearchComplete(isUserFound,id);
                }
            });
        }
    }


    public interface DocsRetrievedListener
    {
        void onDocsRetrieved(Task<QuerySnapshot> task,boolean isUsernameExist);
    }

    public void ScanUsers(DocsRetrievedListener callback){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            callback.onDocsRetrieved(task,false);
                        }
                        else
                        {
                            callback.onDocsRetrieved(null,false);
                        }
                    }
                });
    }

    public interface ScanComplete
    {
        void onScanComplete(boolean flag);
    }

    //check if username exist/taken
    public void DataConfirm(String username,String password, ScanComplete callback)
    {
        if (username != null && password != null){

            ScanUsers(new DocsRetrievedListener() {
                @Override
                public void onDocsRetrieved(Task<QuerySnapshot> task, boolean isUsernameExist) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getData().get("username").toString();
                        String pass = document.getData().get("password").toString();
                        if(username.equals(name)){
                            isUsernameExist = true;
                        }
                    }
                    callback.onScanComplete(!isUsernameExist);
                }
            });

        }
    }

    public interface AddComplete{
        void onAddComplete(boolean flag,String id);
    }

    //add new user to firebase
    public void AddUser(String username,String password,AddComplete callback)
    {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(context, "User added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        callback.onAddComplete(true,documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error adding User", Toast.LENGTH_SHORT).show();
                        callback.onAddComplete(false,"");
                    }
                });
    }

    public interface DeleteComplete{
        void onDeleteComplete(boolean flag);
    }

    //delete user from firebase
    public void DeleteUser(DeleteComplete callback){
        db.collection("users").document(CurrentUser.getFireId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callback.onDeleteComplete(true);
                }
                else{
                    callback.onDeleteComplete(false);
                }
            }
        });
    }


    public interface UpdateComplete{
        void onUpdateComplete(boolean flag);
    }

    //update user in firebase
    public void UpdateUser(String fireId,String username,String password,UpdateComplete callback)
    {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);

        // Add a new document with a generated ID
        db.collection("users").document(fireId).update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(context, "User updated with ID: " + CurrentUser.getFireId(), Toast.LENGTH_SHORT).show();
                    callback.onUpdateComplete(true);
                }
                else
                {
                    Toast.makeText(context, "Error updating User", Toast.LENGTH_SHORT).show();
                    callback.onUpdateComplete(false);
                }
            }
        });
    }





}
