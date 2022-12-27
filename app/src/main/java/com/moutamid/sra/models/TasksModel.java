package com.moutamid.sra.models;

public class TasksModel {
    String id, name;
    int amount, income, image;
    boolean isLock;

    public TasksModel() {
    }

    public TasksModel(String id, String name, int amount, int income, int image, boolean isLock) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.income = income;
        this.image = image;
        this.isLock = isLock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
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
}
