package com.example.prototype;

public class AccountPilgrim {
    private String FirstName;
    private String LastName;
    private int Age;
    private String Nationality;
    private String email;
    private String password;
    private String Medical_background;
    private String diagnosis;
    private String idLocation;
    private String ID;
    private String ST;
    private String HR;
    private String BP;
    private String SpO2;


    public AccountPilgrim() {
    }

    public AccountPilgrim(String ID) {
        this.ID = ID;
    }

    public AccountPilgrim(String firstName, String lastName, int age, String nationality, String email,
                          String password, String medical_background, String diagnosis, String idLocation, String ST, String HR, String BP, String spO2) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Nationality = nationality;
        this.email = email;
        this.password = password;
        Medical_background = medical_background;
        this.diagnosis = diagnosis;
        this.idLocation = idLocation;
        this.ST = ST;
        this.HR = HR;
        this.BP = BP;
        SpO2 = spO2;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }

    public void setBP(String BP) {
        this.BP = BP;
    }

    public void setSpO2(String spO2) {
        SpO2 = spO2;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMedical_background(String medical_background) {
        Medical_background = medical_background;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getAge() {
        return Age;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMedical_background() {
        return Medical_background;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getID() {
        return ID;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public String getST() {
        return ST;
    }

    public String getHR() {
        return HR;
    }

    public String getBP() {
        return BP;
    }

    public String getSpO2() {
        return SpO2;
    }
}
