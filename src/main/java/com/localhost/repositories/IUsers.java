package com.localhost.repositories;

import com.localhost.model.User;

import java.util.List;

public interface IUsers {
    void addUser(User user);
    int getUsersSize();
    List<User> getUsers();
}
