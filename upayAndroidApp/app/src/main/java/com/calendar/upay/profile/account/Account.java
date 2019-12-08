package com.calendar.upay.profile.account;

public class Account {

    private  String number,type;
    private double limitAmount,utilizeAmount;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public double getUtilizeAmount() {
        return utilizeAmount;
    }

    public void setUtilizeAmount(double utilizeAmount) {
        this.utilizeAmount = utilizeAmount;
    }
}
