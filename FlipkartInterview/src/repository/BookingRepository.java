package repository;

import java.util.*;

public class BookingRepository {
    Set<String> bookedUserIds = new HashSet<>();
    Map<String, List<Integer>> centreToBookedUsersMap = new HashMap<>();

    public Set<String> getBookedUserIds() {
        return bookedUserIds;
    }

    public void setBookedUserIds(Set<String> bookedUserIds) {
        this.bookedUserIds = bookedUserIds;
    }

    public Map<String, List<Integer>> getCentreToBookedUsersMap() {
        return centreToBookedUsersMap;
    }

    public void setCentreToBookedUsersMap(Map<String, List<Integer>> centreToBookedUsersMap) {
        this.centreToBookedUsersMap = centreToBookedUsersMap;
    }
}
