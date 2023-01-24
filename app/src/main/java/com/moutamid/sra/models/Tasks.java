package com.moutamid.sra.models;

import androidx.room.ColumnInfo;

public class Tasks {
    String id;
    String name;
    int amount;
    float income;
    int image;
    boolean isLock;
    int total;

    public Tasks() {
    }

    public Tasks(String name, int amount, float income, int image, boolean isLock, int total) {
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    public Tasks(String id, String name, int amount, float income, int image, boolean isLock, int total) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
