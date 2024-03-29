package com.localhost.in;

import com.localhost.model.*;

public class UserSession implements IUserSession{

    private User user;
    private String login = null;
    private IModel model;

    public UserSession() {
        model = new Model();
    }

//    @Override
//    public boolean createUser(String login, String password, String phoneNumber, String address) {
//        user = new User(login, password, phoneNumber, address);
//        return Users.addUser(user);
//    }

    @Override
    public boolean logIn(String user, String password) {
        boolean answer = false;
        if(model.getUsers().getUser(user).getPassword().equals(password)) {
            login = user;
            answer = true;
        }
        return answer;
    }

    @Override
    public void logout() {
        login = null;
    }

    @Override
    public boolean isAdmin() {
        return model.getUsers().getUser(login).getIsAdmin();
    }

    @Override
    public boolean isUser() {
        return !model.getUsers().getUser(login).getIsAdmin();
    }

    @Override
    public String getLogin() {
        return login;
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
        return new AdminSession(this);
    }

    @Override
    public IModel getModel() {
        return model;
    }
}
