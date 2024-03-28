package com.localhost.in;

import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.User;
import com.localhost.model.Users;

public class UserSession implements IUserSession{

    private User user;

    @Override
    public boolean createUser(String login, String password, String phoneNumber, String address) {
        user = new User(login, password, phoneNumber, address);
        return Users.addUser(user);
    }

    @Override
    public boolean logIn(String user, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isAdmin() {
        return getLogin().equals("admin");
    }

    @Override
    public boolean isUser() {
        return !getLogin().equals("admin");
    }

    @Override
    public String getLogin() {
        return user.getLogin();
    }

    @Override
    public CounterValue getLastValue(CounterType counter) {
        return null;
    }

    @Override
    public void addCounterValue(CounterType counter, CounterValue value) throws AddCounterException {

    }

    @Override
    public IAdminSession getAdminSession() {
        return null;
    }
}
