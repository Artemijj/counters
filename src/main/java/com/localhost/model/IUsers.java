package com.localhost.model;

import java.util.ArrayList;

public interface IUsers {
    ArrayList<User> getUserList();
    boolean addUser(User user);
    void deleteUser(User user);
    User getUser(String login);
}
