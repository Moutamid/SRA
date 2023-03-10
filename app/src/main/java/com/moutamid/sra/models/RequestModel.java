package com.moutamid.sra.models;

public class RequestModel {
    String ID, image, userID, hashKey;
    float amount;
    long timestamps;
    String status, type;
    String uid;
    String name;
    float income;
    boolean isLock;

    public RequestModel() {
    }

    public RequestModel(String ID, String imageLink, String userID, String hashKey, float amount, long timestamps, String status, String type) {
        this.ID = ID;
        this.image = imageLink;
        this.userID = userID;
        this.hashKey = hashKey;
        this.amount = amount;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }

    public RequestModel(String ID, String userID, String hashKey, String status, String type, float amount, long timestamps) {
        this.ID = ID;
        this.userID = userID;
        this.hashKey = hashKey;
        this.amount = amount;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }

    public RequestModel(String ID, String imageLink, String userID, float amount, long timestamps, String status, String type) {
        this.ID = ID;
        this.image = imageLink;
        this.userID = userID;
        this.amount = amount;
        this.timestamps = timestamps;
        this.status = status;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        this.timestamps = timestamps;
    }
}
