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

    public static void ClearUser(){
        username = "";
        password = "";
        fireId = "";
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

    public static String getFireId() {
        return fireId;
    }

    public static void setFireId(String fireId) {
        CurrentUser.fireId = fireId;
    }
}
