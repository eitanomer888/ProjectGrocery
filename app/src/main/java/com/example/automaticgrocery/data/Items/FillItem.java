package com.example.automaticgrocery.data.Items;

public class FillItem {

    //variables
    private String name;
    private String internal_reference;
    private int amount;
    private int targetAmount;

    //constructor
    public FillItem(String name, String internal_reference, int amount, int targetAmount) {
        this.name = name;
        this.internal_reference = internal_reference;
        this.amount = amount;
        this.targetAmount = targetAmount;
    }

    //getters and setters//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternal_reference() {
        return internal_reference;
    }

    public void setInternal_reference(String internal_reference) {
        this.internal_reference = internal_reference;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    //############//
}
