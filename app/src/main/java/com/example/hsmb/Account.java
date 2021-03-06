package com.example.hsmb;

public class Account {
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


    public Account() {
    }

    public Account(String ID) {
        this.ID = ID;
    }

    public Account(String firstName, String lastName, int age, String nationality, String email,
                          String password, String medical_background, String diagnosis, String idLocation) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Nationality = nationality;
        this.email = email;
        this.password = password;
        Medical_background = medical_background;
        this.diagnosis = diagnosis;
        this.idLocation = idLocation;
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

}