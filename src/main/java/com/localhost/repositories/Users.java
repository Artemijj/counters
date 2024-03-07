package com.localhost.repositories;

import com.localhost.model.User;

import java.util.ArrayList;
import java.util.List;

public class Users implements IUsers{
//    private User user;
    private static final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public int getUsersSize() {
        return users.size();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
