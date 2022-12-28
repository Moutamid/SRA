package com.moutamid.sra.models;

public class WithdrawRequestModel {
    String ID, hashKey, userID;
    int amount;
    long timestamps;
    String status, type;

    public WithdrawRequestModel() {
    }

    public WithdrawRequestModel(String ID, String hashKey, String userID, int amount) {
        this.ID = ID;
        this.hashKey = hashKey;
        this.userID = userID;
        this.amount = amount;
    }

    public WithdrawRequestModel(String ID, String hashKey, String userID, int amount, long timestamps, String status, String type) {
        this.ID = ID;
        this.hashKey = hashKey;
        this.userID = userID;
        this.amount = amount;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        this.timestamps = timestamps;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
