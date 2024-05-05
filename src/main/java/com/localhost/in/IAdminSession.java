package com.localhost.in;

import com.localhost.model.CounterValue;
import com.localhost.model.Event;
import com.localhost.model.User;

public interface IAdminSession {
    void addUser(String login, String password) throws AdminException;
    void addAdmin(String login, String password) throws AdminException;
    void removeUser(String login) throws AdminException;
    User[] getAllUsers() throws AdminException;
    User getUserData(String login) throws AdminException;

    void setPassword(String login, String password) throws AdminException;
    void setAddress(String login, String address) throws AdminException;
    void setPhone(String login, String phone) throws AdminException;

    void createCounter(String counterName) throws AdminException;

    String[] getAllSystemCounters();
    String[] getUserCounters(String login) throws AdminException;
    void linkCounter(String login, String counter) throws AdminException;
    void unlinkCounter(String login, String counter) throws AdminException;
    CounterValue[] getCounterValues(String login, String counter) throws AdminException;
    Event[] getUserActivities(String login) throws AdminException;
    void setFio(String login, String fio) throws AdminException;
}
