package com.example.prototype;



public class PilgrimModel {
    private int ID;
    private String firstname;
    private String lastname;
    private int age;
    private int nationality ;

    private String username;
    private String Password;

    private String medicalbackground ;
    private String diagnosis;
    private int Idlocation;


    //constructors


    public PilgrimModel(int ID, String firstname, String lastname, int age, int nationality, String username, String password, String medicalbackground, String diagnosis, int idlocation) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.nationality = nationality;
        this.username = username;
        Password = password;
        this.medicalbackground = medicalbackground;
        this.diagnosis = diagnosis;
        Idlocation = idlocation;
    }

    public PilgrimModel(String username,String password) {
        this.username = username;
        Password = password;
    }

    public PilgrimModel() {
    }


    //getter and setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMedicalbackground() {
        return medicalbackground;
    }

    public void setMedicalbackground(String medicalbackground) {
        this.medicalbackground = medicalbackground;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getIdlocation() {
        return Idlocation;
    }

    public void setIdlocation(int idlocation) {
        Idlocation = idlocation;
    }

}
