package com.example.automaticgrocery.data.Items;

public class CurrentUser {
    static String username;
    static  String password;

    static String fireId;

    public CurrentUser() {

    }

    public static void InitializeUser(String Username, String Password, String FireId){
        username = Username;
        password = Password;
        fireId = FireId;
    }
}
