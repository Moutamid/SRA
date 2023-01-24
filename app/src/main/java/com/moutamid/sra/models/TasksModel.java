package com.moutamid.sra.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TaskTable")
public class TasksModel implements Serializable {
    @NonNull
    @PrimaryKey
    String id ="0";
    @ColumnInfo(name = "uid")
    String uid = "";
    @ColumnInfo(name = "name")
    String name = "";
    @ColumnInfo(name = "amount")
    int amount = 0;
    @ColumnInfo(name = "income")
    float income = 0.0F;
    @ColumnInfo(name = "image")
    int image;
    @ColumnInfo(name = "isLock")
    boolean isLock = true;
    @ColumnInfo(name = "total")
    int total = 0;

    @ColumnInfo(name = "userID")
    String userID = "";

    @ColumnInfo(name = "timestamp")
    long timestamps = 0;

    @ColumnInfo(name = "status")
    String status = "";

    @ColumnInfo(name = "type")
    String type = "";

    @Ignore
    public TasksModel() {
    }

    @Ignore
    public TasksModel(String name, int amount, float income, int image, boolean isLock, int total) {
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    @Ignore
    public TasksModel(String id, String uid, String name, int amount, float income, boolean isLock, int total, String userID, long timestamps, String status, String type) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.isLock = isLock;
        this.total = total;
        this.userID = userID;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }

    @Ignore
    public TasksModel(String uid, String name, int amount, float income, int image, boolean isLock, int total) {
        this.uid = uid;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
        this.total = total;
    }

    public TasksModel(String uid, String name, int amount, float income, boolean isLock, int total, String userID, long timestamps, String status, String type) {
        this.uid = uid;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.isLock = isLock;
        this.total = total;
        this.userID = userID;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        this.timestamps = timestamps;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
}
