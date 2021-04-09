package repository;

import model.Centre;
import model.VaccineType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VaccineRepository {
    private Map<VaccineType, Set<String>> vaccineTypeToCentreMap = new HashMap<>();

    public Map<VaccineType, Set<String>> getVaccineTypeToCentreMap() {
        return vaccineTypeToCentreMap;
    }
}
