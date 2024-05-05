package com.localhost.in;

import com.localhost.model.*;
import com.localhost.model.Record;
import com.localhost.model.model.IModel;
import com.localhost.model.model.ModelList;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.userCounters.IUserCounters;
import com.localhost.model.users.IUsers;

import java.util.Comparator;
import java.util.Date;
import java.util.NoSuchElementException;

public class UserSession implements IUserSession{

    private String login = "";
    private IModel model;

    public UserSession() {
        model = new ModelList();
    }

    @Override
    public boolean logIn(String name, String password) {
        boolean answer = false;
        if(isUserExist(name) && model.getUsers().getUser(name).getPassword().equals(password)) {
            login = name;
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
    public CounterValue getLastValue(String counter) {
        Date maxDate;

        try {
            maxDate = model.getRecordSet().getRecordSetList()
                    .stream()
//                    .filter(record -> record.getLogin().equals(model.getUsers().getUser(login)))
                    .filter(record -> record.getLogin().equals(login))
                    .filter(record -> record.getCounterType().equals(counter))
                    .map(Record::getCounterValue)
                    .map(CounterValue::getDate)
                    .max(Comparator.naturalOrder())
                    .get();
        } catch (NoSuchElementException e) {
//            e.printStackTrace();
            return new CounterValue(new Date(0L), 0);
        }
        return model.getRecordSet().getRecordSetList()
                .stream()
//                .filter(record -> record.getLogin().equals(model.getUsers().getUser(login)))
                .filter(record -> record.getLogin().equals(login))
                .filter(record -> record.getCounterType().equals(counter))
                .filter(record -> record.getCounterValue().getDate().equals(maxDate))
                .map(Record::getCounterValue).findFirst().orElse(null);
    }

    @Override
    public void addCounterValue(String counter, CounterValue value) throws AddCounterException {
//        ????????????????????????????????????
    }

    @Override
    public IAdminSession getAdminSession() {
        return new AdminSession(this);
    }

    @Override
    public IUsers getModelUsers() {
        return model.getUsers();
    }

    @Override
    public IUserCounters getModelUserCounters() {
        return model.getUserCounters();
    }

    @Override
    public ISystemCounters getModelSystemCounters() {
        return model.getSystemCounters();
    }

    @Override
    public IRecordSet getModelRecordSet() {
        return model.getRecordSet();
    }

    @Override
    public IEventLog getModelEventLog() {
        return model.getEventLog();
    }


    @Override
    public void addEvent(String event) {
        model.getEventLog().addEvent(new Event(Tools.nextId(model.getEventLog().getEventLogList()), getLogin(), new Date(), event));
    }

    @Override
    public boolean isUserExist(String login) {
        return getModelUsers().getUserList().contains(getModelUsers().getUser(login));
    }

    @Override
    public void addCounter(String counterType) {
////        getModelUserCounters().getUserCountersList().add(new UserCounter(getLogin(), counterType.getCounterTypeName()));
//        getModelUserCounters().addUserCounter(new UserCounter(model.getUserCounters().nextId(), getLogin(), counterType.getCounterTypeName()));
        try {
            getAdminSession().linkCounter(getLogin(), counterType);
        } catch (AdminException e) {
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCounter(String counterType) {
//        UserCounter userCounter = getModelUserCounters().getUserCountersList().stream()
//                .filter(uC -> uC.getLogin().equals(getLogin()))
//                .filter(uC -> uC.getCounterName().equals(counterType.getCounterTypeName()))
//                .findFirst().orElse(null);
//        if (!Objects.isNull(userCounter)) {
//            getModelUserCounters().deleteUserCounter(userCounter);
//        }
        try {
            getAdminSession().unlinkCounter(getLogin(), counterType);
        } catch (AdminException e) {
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }
}
