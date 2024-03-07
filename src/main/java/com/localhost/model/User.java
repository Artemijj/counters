package com.localhost.model;

import com.localhost.repositories.Users;

import java.util.List;

public class User implements IUser{

    private String userName;
    private int id;
    private String address;
    private String phoneNumber;
    private String password;
    private List<Counter> userCounters;
    private final Users users;

    public User(String userName, Users users, String address, String phoneNumber, String password) {
        this.userName = userName;
        this.users = users;
        this.id = users.getUsersSize();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public void createUser() {

    }

    @Override
    public void loginUser() {

    }

    @Override
    public void logoutUser() {

    }

    @Override
    public void addCounter(Counter counter) {
        userCounters.add(counter);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Counter> getUserCounters() {
        return userCounters;
    }

    public void setUserCounters(List<Counter> userCounters) {
        this.userCounters = userCounters;
    }
}
