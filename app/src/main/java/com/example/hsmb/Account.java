package com.example.hsmb;

public class Account {
    private String FirstName;
    private String LastName;
    private int Age;
    private String Nationality;
    private String Medical_background;
    private String Eamil;
    private String diagnosis;
    private String password;

    public Account() {
    }

    public Account(String firstName, String lastName, int age, String nationality, String medical_background, String eamil, String diagnosis, String password) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Nationality = nationality;
        Medical_background = medical_background;
        Eamil = eamil;
        this.diagnosis = diagnosis;
        this.password = password;
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

    public String getMedical_background() {
        return Medical_background;
    }

    public String getEamil() {
        return Eamil;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPassword() {
        return password;
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

    public void setMedical_background(String medical_background) {
        Medical_background = medical_background;
    }

    public void setEamil(String eamil) {
        Eamil = eamil;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
