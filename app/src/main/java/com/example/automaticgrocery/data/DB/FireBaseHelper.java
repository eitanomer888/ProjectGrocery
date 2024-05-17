package com.example.automaticgrocery.data.DB;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
        void onDocsRetrieved(Task<QuerySnapshot> task);
    }



    public void AddUser(String username,String password)
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
                        Toast.makeText(context, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error adding document", Toast.LENGTH_SHORT).show();
                    }
                });
    }










}
