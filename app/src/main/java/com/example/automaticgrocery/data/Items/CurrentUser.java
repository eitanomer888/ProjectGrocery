package com.example.automaticgrocery.data.Items;

public class CurrentUser {
    static String username;
    static  String password;

    static String fireId;

    //constructor
    public CurrentUser() {

    }

    //initialize current user with data
    public static void InitializeUser(String Username, String Password, String FireId){
        username = Username;
        password = Password;
        fireId = FireId;
    }

    //clear current user data
    public static void ClearUser(){
        username = "";
        password = "";
        fireId = "";
    }

    //getters and setters//
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

    //#################//
}
