package com.moutamid.sra.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TaskTable")
public class TasksModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id =0;
    @ColumnInfo(name = "name")
    String name = "";
    @ColumnInfo(name = "amount")
    int amount = 0;
    @ColumnInfo(name = "income")
    float income = 0.0F;
    @ColumnInfo(name = "image")
    int image = 0;
    @ColumnInfo(name = "isLock")
    boolean isLock = true;
    @ColumnInfo(name = "total")
    int total = 0;

    public TasksModel(int id, String name, int amount, float income, int image, boolean isLock, int total) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    public TasksModel(String name, int amount, float income, int image, boolean isLock, int total) {
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
