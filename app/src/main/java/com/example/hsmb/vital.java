package com.example.hsmb;

public class vital {
   private String ST;
    private String SpO2;

    public vital() {

    }

    public vital(String ST, String spO2) {
        this.ST = ST;
        SpO2 = spO2;
    }

    public String getST() {
        return ST;
    }

    public String getSpO2() {
        return SpO2;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public void setSpO2(String spO2) {
        SpO2 = spO2;
    }
}
