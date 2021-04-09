package repository;

import model.Centre;

import java.util.*;

public class CentreRepository {
    Map<String, Centre> centreIdToCentreMap;

    public Map<String, Centre> getCentreIdToCentreMap() {
        return centreIdToCentreMap;
    }

    public CentreRepository() {
        centreIdToCentreMap = new HashMap<>();
    }

    public void addCentre(String centreName, Centre centre) {
        centreIdToCentreMap.put(centreName, centre);
    }

    public boolean containsCentre(String centreName) {
        return centreIdToCentreMap.containsKey(centreName);
    }

    public List<Centre> getCentre(Set<String> centres) {
        List<Centre> centre = new ArrayList<>();
        for (String name : centres)
            if (containsCentre(name)) {
                centre.add( centreIdToCentreMap.get(name));
            }

            return centre;
    }

    public Centre getCentre(String centreId) {
        return centreIdToCentreMap.get(centreId);
    }
}
