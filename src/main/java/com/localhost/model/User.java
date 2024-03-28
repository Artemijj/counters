package com.localhost.model;

import com.localhost.repositories.Users;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String login;
    private String address;
    private String phoneNumber;
    private String password;
    private ArrayList<CounterType> userCounters;

    public User(String login, String password, String phoneNumber, String address) {
        this.login = login;
        this.address = password;
        this.phoneNumber = phoneNumber;
        this.password = address;
        userCounters = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addCounter(CounterType counterType) {
        userCounters.add(counterType);
    }

    public void deleteCounter(CounterType counterType) {
        userCounters.remove(counterType);
    }

    public ArrayList<CounterType> getUserCounters() {
        return userCounters;
    }
}
