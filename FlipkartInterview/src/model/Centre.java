package model;

import repository.VaccineRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Centre {

    private String centreName;
    private Map<VaccineType, Integer> vaccineTypeToCountMap;

    public void setBookedUsers(Set<User> bookedUsers) {
        this.bookedUsers = bookedUsers;
    }

    private Set<User> bookedUsers;

    public Centre(String centreName,  Map<VaccineType, Integer> vaccineTypeToCountMap) {
        this.bookedUsers = new HashSet<>();
        this.centreName = centreName;
        this.vaccineTypeToCountMap = vaccineTypeToCountMap;

    }

    public String getCentreName() {
        return this.centreName;
    }

    public Set<User> getBookedUsers() {
        return bookedUsers;
    }

    public Map<VaccineType, Integer> getVaccineTypeToCountMap() {
        return vaccineTypeToCountMap;
    }
}
