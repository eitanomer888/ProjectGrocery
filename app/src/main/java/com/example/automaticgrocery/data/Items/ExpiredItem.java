package com.example.automaticgrocery.data.Items;

import java.util.Date;

public class ExpiredItem {

    //variables
    private String name;
    private String last_date;
    private int amount;
    private String internal_reference;
    private int last_date_amount;


    // Constructor
    public ExpiredItem(String name, String last_date, int amount, String internal_reference, int last_date_amount) {
        this.name = name;
        this.last_date = last_date;
        this.amount = amount;
        this.internal_reference = internal_reference;
        this.last_date_amount = last_date_amount;
    }

    //getters + setters//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getInternal_reference() {
        return internal_reference;
    }

    public void setInternal_reference(String internal_reference) {
        this.internal_reference = internal_reference;
    }

    public int getLast_date_amount() {
        return last_date_amount;
    }

    public void setLast_date_amount(int last_date_amount) {
        this.last_date_amount = last_date_amount;
    }

    //###############//
}
