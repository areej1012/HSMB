package com.example.prototype;

public class PhacrmacyP {
    private String ExpiryDate;
    private int number;
    private String name;


    public PhacrmacyP() {
    }

    public PhacrmacyP(String expiryDate, int number, String name) {
        ExpiryDate = expiryDate;
        this.number = number;
        this.name = name;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
