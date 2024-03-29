package com.localhost.model;

import java.util.ArrayList;

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
        return (User) users.stream().filter(user -> user.getLogin().equals(login));
    }
}
