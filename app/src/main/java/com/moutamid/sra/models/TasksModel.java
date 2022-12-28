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
    int income = 0;

    @ColumnInfo(name = "image")
    int image = 0;

    @ColumnInfo(name = "isLock")
    boolean isLock = true;

    public TasksModel(String name, int amount, int income, int image, boolean isLock) {
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
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

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
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
