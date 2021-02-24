package com.example.hsmb;

public class AccountPligrim {
    private int id;
    private  String firstName;
    private String lastNmae;
    private int age;
    private  String Nationality;
    private String username;
    private String password;
    private String Medical_background;
    private String diagnosis;
    private int idLocation;
//consutrcter


    public AccountPligrim(int id, String firstName, String lastNmae, int age, String nationality,
                          String username, String password, String medical_background, String diagnosis, int idLocation) {
        this.id = id;
        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.age = age;
        Nationality = nationality;
        this.username = username;
        this.password = password;
        Medical_background = medical_background;
        this.diagnosis = diagnosis;
        this.idLocation = idLocation;
    }

    public AccountPligrim() {
    }
//getters
    public int getId() {
        return id;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getUsername() {
        return username;
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
    //toString for print

    @Override
    public String toString() {
        return "AccountPligrim{" +
                "id=" + id +
                ", idLocation=" + idLocation +
                ", firstName='" + firstName + '\'' +
                ", lastNmae='" + lastNmae + '\'' +
                ", age=" + age +
                ", Nationality='" + Nationality + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Medical_background='" + Medical_background + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
