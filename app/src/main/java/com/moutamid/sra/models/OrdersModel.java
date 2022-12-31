package com.moutamid.sra.models;

public class OrdersModel {
    String ID, name;
    int quantity;
    double price, total;
    int image;
    String orderTime;
    int commission;

    public OrdersModel() {
    }

    public OrdersModel(String ID, String name, double price, int quantity, double total, int image, String orderTime, int commission) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.image = image;
        this.orderTime = orderTime;
        this.commission = commission;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
}
