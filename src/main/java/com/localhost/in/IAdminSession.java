package com.localhost.in;

import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.User;

public interface IAdminSession {
    void addUser(String login, String password) throws AdminException;
    void addAdmin(String login, String password) throws AdminException;
    void removeUser(String login) throws AdminException;
    User[] getAllUsers() throws AdminException;
    User getUserData(String login) throws AdminException;

    void SetPassword(String login, String password) throws AdminException;
    void setAddress(String login, String address) throws AdminException;
    void setPhone(String login, String phone) throws AdminException;

    void createCounter(String counterName) throws AdminException;

    CounterType[] getAllSystemCounters();
    CounterType[] getUserCounters(String login) throws AdminException;
    void linkCounter(String login, CounterType counter) throws AdminException;
    void unlinkCounter(String login, CounterType counter) throws AdminException;
    CounterValue[] getCounterValues(String login, CounterType counter) throws AdminException;
}
