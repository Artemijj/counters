package com.localhost.model.users;

import com.localhost.model.User;

import java.util.ArrayList;

public class UsersList implements IUsers{

    private static ArrayList<User> users;

    public UsersList() {
        users = new ArrayList<>();
    }

    @Override
    public ArrayList<User> getUserList() {
        return users;
    }

    @Override
    public boolean addUser(User user) {
        boolean answer = false;
        long userCount = users.stream()
                .filter(u -> u.getLogin().equals(user.getLogin())).count();
        if ((userCount == 0) && (!users.contains(user))) {
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
