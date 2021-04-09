package repository;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private Map<String, User> userIdToUserMap;

    public UserRepository() {
        userIdToUserMap = new HashMap<>();
    }

    public void addUser(String name, User user) {
        userIdToUserMap.put(name, user);
    }

    public List<User> getUsers(List<String> userIds) {
        List<User> users = new ArrayList<>();
        for (String id : userIds) {
            if (userIdToUserMap.containsKey(id))
                users.add(userIdToUserMap.get(id));
        }
        return users;
    }

    public User getUser(String userId) {
        if (userIdToUserMap.containsKey(userId))
            return userIdToUserMap.get(userId);

        return null;
    }

    public boolean containsUser(String userid) {
        return this.userIdToUserMap.containsKey(userid);
    }
}
