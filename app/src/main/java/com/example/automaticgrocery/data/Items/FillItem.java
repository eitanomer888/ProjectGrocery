package com.example.automaticgrocery.data.Items;

public class FillItem {
    private String name;
    private String internal_reference;
    private int amount;

    public FillItem(String name, String internal_reference, int amount) {
        this.name = name;
        this.internal_reference = internal_reference;
        this.amount = amount;
    }

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
}
