package com.localhost.model.users;

import com.localhost.model.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Users implements IUsers{

    private static ArrayList<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    @Override
    public ArrayList<User> getUserList() {
        return users;
    }

    @Override
    public boolean addUser(User user) {
        boolean answer = false;
        if (!users.contains(user)) {
            users.add(user);
            answer = true;
        }
        return answer;
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }

    @Override
    public User getUser(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .map(User.class::cast)
                .findFirst()
                .orElse(null);
    }
}
