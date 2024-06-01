package com.example.automaticgrocery.data.Items;

public class AllItem {

    //variables
    private String internal_reference,name,barcode;
    private int amount;
    private String fill_date,last_date,category;
    private int target_amount,last_date_amount;


    //constructor
    public AllItem(String internal_reference, String name, String barcode, int amount, String fill_date, String last_date, String category, int target_amount, int last_date_amount) {
        this.internal_reference = internal_reference;
        this.name = name;
        this.barcode = barcode;
        this.amount = amount;
        this.fill_date = fill_date;
        this.last_date = last_date;
        this.category = category;
        this.target_amount = target_amount;
        this.last_date_amount = last_date_amount;
    }

    //getters and setters//
    public String getInternal_reference() {
        return internal_reference;
    }

    public void setInternal_reference(String internal_reference) {
        this.internal_reference = internal_reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFill_date() {
        return fill_date;
    }

    public void setFill_date(String fill_date) {
        this.fill_date = fill_date;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTarget_amount() {
        return target_amount;
    }

    public void setTarget_amount(int target_amount) {
        this.target_amount = target_amount;
    }

    public int getLast_date_amount() {
        return last_date_amount;
    }

    public void setLast_date_amount(int last_date_amount) {
        this.last_date_amount = last_date_amount;
    }

    //###########//
}
