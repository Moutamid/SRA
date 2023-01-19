package com.moutamid.sra.models;

public class TeamModel {
    String key, name;
    double earn;

    public TeamModel() {
    }

    public TeamModel(String key, double earn) {
        this.key = key;
        this.earn = earn;
    }

    public TeamModel(String key, String name, double earn) {
        this.key = key;
        this.name = name;
        this.earn = earn;
    }

    public TeamModel(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getEarn() {
        return earn;
    }

    public void setEarn(double earn) {
        this.earn = earn;
    }
}
