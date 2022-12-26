package com.moutamid.sra.models;

public class DepositRequestModel {
    String ID, image, userID;
    int amount;

    public DepositRequestModel() {}

    public DepositRequestModel(String ID, String image, int amount, String userID) {
        this.ID = ID;
        this.image = image;
        this.amount = amount;
        this.userID = userID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}