package com.moutamid.sra.models;

public class DepositRequestModel {
    String ID, image, amount, userID;

    public DepositRequestModel() {}

    public DepositRequestModel(String ID, String image, String amount, String userID) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
