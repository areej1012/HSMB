package com.example.hsmb;

public class booths {
    private String IDLocation;
    private String State;
    private String latitude;
    private String longitude;
    private String IDAccount;
    private String IDdecoment;

    public booths() {
    }

    public booths(String IDdecoment) {
        this.IDdecoment = IDdecoment;
    }

    public booths(String IDLocation, String state, String latitude, String longitude, String IDAccount) {
        this.IDLocation = IDLocation;
        State = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.IDAccount = IDAccount;
    }

    public void setIDAccount(String IDAccount) {
        this.IDAccount = IDAccount;
    }

    public void setIDdecoment(String IDdecoment) {
        this.IDdecoment = IDdecoment;
    }

    public String getIDdecoment() {
        return IDdecoment;
    }

    public void setIDLocation(String IDLocation) {
        this.IDLocation = IDLocation;
    }

    public void setState(String state) {
        State = state;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIDLocation() {
        return IDLocation;
    }

    public String getState() {
        return State;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getIDAccount() {
        return IDAccount;
    }
}
