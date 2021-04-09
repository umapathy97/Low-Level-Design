package model;

public class User {
    private String name;
    private Gender gender;
    private VaccineType preferredVaccineType;



    public User(String userName, VaccineType vaccineType, Gender gender) {
        this.gender = gender;
        this.preferredVaccineType = vaccineType;
        this.name = userName;
    }

    public VaccineType getPreferredVaccineType() {
        return preferredVaccineType;
    }

    public String getName() {
        return name;
    }
}
