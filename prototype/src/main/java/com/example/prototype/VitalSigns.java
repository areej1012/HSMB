package com.example.prototype;

public class VitalSigns {
    private String ST;
    private String HR;
    private String BP;
    private String SpO2;

    public VitalSigns() {
    }

    public VitalSigns(String ST, String HR, String BP, String spO2) {
        this.ST = ST;
        this.HR = HR;
        this.BP = BP;
        SpO2 = spO2;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getHR() {
        return HR;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }

    public String getBP() {
        return BP;
    }

    public void setBP(String BP) {
        this.BP = BP;
    }

    public String getSpO2() {
        return SpO2;
    }

    public void setSpO2(String spO2) {
        SpO2 = spO2;
    }
}
