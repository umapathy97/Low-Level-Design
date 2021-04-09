package Service;

import model.Centre;
import model.Gender;
import model.User;
import model.VaccineType;
import repository.BookingRepository;
import repository.CentreRepository;
import repository.UserRepository;
import repository.VaccineRepository;

import java.util.*;

public class UserService {
    UserRepository userRepository = new UserRepository();
    CentreRepository centreRepository = new CentreRepository();
    VaccineRepository vaccineRepository = new VaccineRepository();
    BookingRepository bookingRepository = new BookingRepository();

    public boolean registerUser(String userName, VaccineType vaccineType, Gender gender) throws IllegalAccessException {
        if (userRepository.containsUser(userName)) {
            throw new IllegalAccessException();
        }
        else {
            User user = new User(userName, vaccineType, gender);
            userRepository.addUser(userName, user);
        }
        return true;
    }

    public boolean bookSlot(String userId, String centreId) {
        if (bookingRepository.getBookedUserIds().contains(userId)) {
            System.out.println("user is already booked");
            return false;
        }
        if (!userRepository.containsUser(userId)) {
            System.out.println("user not present");
            return false;
        }

        if (!centreRepository.containsCentre(centreId)) {
            System.out.println("centre not present");
            return false;
        }
        User user = userRepository.getUser(userId);
       if (centreRepository.getCentre(centreId).getVaccineTypeToCountMap().get(user.getPreferredVaccineType()) == null) {
           System.out.println(" no centers had been present with user preference");
           return false;
       }
        centreRepository.getCentre(centreId).getBookedUsers().add(user);
        centreRepository.getCentre(centreId).getVaccineTypeToCountMap().put(user.getPreferredVaccineType(),  centreRepository.getCentre(centreId).getVaccineTypeToCountMap().get(user.getPreferredVaccineType()) - 1);
       bookingRepository.getBookedUserIds().add(userId);
       centreRepository.getCentre(centreId).getBookedUsers().add(user);
       if ( centreRepository.getCentre(centreId).getVaccineTypeToCountMap().get(user.getPreferredVaccineType()) == 0) {
           centreRepository.getCentre(centreId).getVaccineTypeToCountMap().remove(user.getPreferredVaccineType());
           vaccineRepository.getVaccineTypeToCentreMap().get(user.getPreferredVaccineType()).remove(centreId);
       }
       return true;

    }

    public void getStats() {
        for (Centre centre :centreRepository.getCentreIdToCentreMap().values()) {
            Set<String> userNames = new HashSet<>();
            for (User user : centre.getBookedUsers())
                userNames.add(user.getName());

            System.out.println("users : " + userNames + " centre name " + centre.getCentreName() + " vaccine details " + centre.getVaccineTypeToCountMap());
        }
    }


    public boolean registerCentre(String centreName, Map<VaccineType, Integer> vaccineTypeToCountMap) throws IllegalAccessException {
        Centre centre = new Centre(centreName, vaccineTypeToCountMap);
        if (centreRepository.containsCentre(centreName))
            throw new IllegalAccessException();

        centreRepository.addCentre(centreName, centre);
        populateVaccineRepository(vaccineTypeToCountMap, centreName);
        return true;
    }

    public void getCentres(String userName) throws IllegalAccessException {
        if (!userRepository.containsUser(userName))
            throw new IllegalAccessException();

        User user = userRepository.getUser(userName);

        VaccineType userPreferredType = user.getPreferredVaccineType();
        Set<String> centres = vaccineRepository.getVaccineTypeToCentreMap().get(userPreferredType);
        if (centres == null) {
            System.out.println("no centers present");
            return ;
        }
        List<Centre> centers =  sortAndReturnCentres(centreRepository.getCentre(centres), userPreferredType);
        for (Centre centre : centers)
            System.out.println(centre.getCentreName() + " vaccine " + centre.getVaccineTypeToCountMap());
    }

    private List<Centre> sortAndReturnCentres(List<Centre> centre, VaccineType vaccineType) {
        Collections.sort(centre, (a, b) -> a.getVaccineTypeToCountMap().get(vaccineType) == b.getVaccineTypeToCountMap().get(vaccineType)
        ? a.getBookedUsers().size() - b.getBookedUsers().size() : b.getVaccineTypeToCountMap().get(vaccineType) - a.getVaccineTypeToCountMap().get(vaccineType));
        return centre;
    }



    private void populateVaccineRepository(Map<VaccineType, Integer> vaccineTypeToCountMap, String centre) {
        for (Map.Entry<VaccineType, Integer> entry : vaccineTypeToCountMap.entrySet()) {
            vaccineRepository.getVaccineTypeToCentreMap().computeIfAbsent(entry.getKey(), k -> new HashSet<>());
            vaccineRepository.getVaccineTypeToCentreMap().get(entry.getKey()).add(centre);
        }
    }

}
