package com.localhost.in;

import com.localhost.model.*;
import com.localhost.model.Record;

import java.util.Comparator;
import java.util.Date;

public class UserSession implements IUserSession{

//    private User user;
    private String login = "";
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
    public void logOut() {
        login = "";
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
        Date maxDate = model.getRecordSet().getRecordSetList()
                .stream()
                .filter(record -> record.getUser().equals(model.getUsers().getUser(login)))
                .filter(record -> record.getCounterType().equals(counter))
                .map(Record::getCounterValue)
                .map(CounterValue::getDate)
                .max(Comparator.naturalOrder())
                .get();

        return (CounterValue) model.getRecordSet().getRecordSetList()
                .stream()
                .filter(record -> record.getUser().equals(model.getUsers().getUser(login)))
                .filter(record -> record.getCounterType().equals(counter))
                .filter(record -> record.getCounterValue().getDate().equals(maxDate))
                .map(Record::getCounterValue);
    }

    @Override
    public void addCounterValue(CounterType counter, CounterValue value) throws AddCounterException {
//        ????????????????????????????????????
    }

    @Override
    public IAdminSession getAdminSession() {
        return new AdminSession(this);
    }

    @Override
    public IModel getModel() {
        return model;
    }

    @Override
    public void addEvent(String event) {
        model.getEventLog().addEvent(new Event(model.getEventLog().nextId(), model.getUsers().getUser(getLogin()), new Date(), event));
    }
}
