package com.example.automaticgrocery.data.Items;

import java.util.Date;

public class ExpiredItem {
    private String name;
    private String date;
    private int amount;

    public ExpiredItem(String name,String date,int amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
