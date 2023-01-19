package com.moutamid.sra.models;

public class DepositRequestModel {
    String ID, image, userID;
    float amount;
    long timestamps;
    String status, type;

    public DepositRequestModel() {}

    public DepositRequestModel(String ID, String image, int amount, String userID) {
        this.ID = ID;
        this.image = image;
        this.amount = amount;
        this.userID = userID;
    }

    public DepositRequestModel(String ID, String image, String userID, float amount, long timestamps, String status, String type) {
        this.ID = ID;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
