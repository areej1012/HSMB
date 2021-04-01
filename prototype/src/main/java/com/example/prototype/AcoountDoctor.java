package com.example.prototype;

public class AcoountDoctor {
    private String FirstName;
    private String LastName;
    private int Age;
    private String Nationality;
    private String email;
    private String password;
    private String MedicalSpecialty;
    private String idLocation;

    public AcoountDoctor() {
    }

    public AcoountDoctor(String firstName, String lastName, int age, String nationality, String email, String password, String medicalSpecialty, String idLocation) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Nationality = nationality;
        this.email = email;
        this.password = password;
        MedicalSpecialty = medicalSpecialty;
        this.idLocation = idLocation;
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

    public void setMedicalSpecialty(String medicalSpecialty) {
        MedicalSpecialty = medicalSpecialty;
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

    public String getMedicalSpecialty() {
        return MedicalSpecialty;
    }

    public String getIdLocation() {
        return idLocation;
    }
}
