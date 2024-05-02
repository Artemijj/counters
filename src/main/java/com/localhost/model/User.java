package com.localhost.model;

import java.util.ArrayList;

public class User {

    private String login;
    private String address;
    private String phoneNumber;
    private String password;
//    private ArrayList<CounterType> userCounters;
    private boolean isAdmin;
    private String fio;

    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        phoneNumber = "";
        address = "";
//        userCounters = new ArrayList<>();
        this.isAdmin = isAdmin;
        fio = "";
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

//    public void addCounter(CounterType counterType) {
//        if (!userCounters.contains(counterType)) {
//            userCounters.add(counterType);
//        }
//    }

//    public void deleteCounter(CounterType counterType) {
//        userCounters.remove(counterType);
//    }

//    public ArrayList<CounterType> getUserCounters() {
//        return userCounters;
//    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
