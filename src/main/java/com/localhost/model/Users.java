package com.localhost.model;

import java.util.ArrayList;

public class Users {

    private static ArrayList<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static boolean addUser(User user) {
        boolean answer = false;
        if (!users.contains(user)) {
            users.add(user);
            answer = true;
        }
        return answer;
    }

    public static void deleteUser(User user) {
        users.remove(user);
    }

    public static User getUser(String login) {
        return (User) users.stream().filter(user -> user.getLogin().equals(login));
    }
}
