package com.localhost.in;

import com.localhost.model.*;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.userCounters.IUserCounters;
import com.localhost.model.users.IUsers;

public interface IUserSession {
//    boolean createUser(String login, String password, String phoneNumber, String address);
    boolean logIn(String user, String password);
    void logOut();
    boolean isAdmin();
    boolean isUser();
    String getLogin();

    default User getUserData() throws AdminException {
        return getAdminSession().getUserData(getLogin());
    }

    default String[] userCounters() throws AdminException {
        return getAdminSession().getUserCounters(getLogin());
    }

    default CounterValue[] getCounterValues(String counter) throws AdminException {
        return getAdminSession().getCounterValues(getLogin(), counter);
    }

    CounterValue getLastValue(String counter);
    void addCounterValue(String counter, CounterValue value) throws AddCounterException;
    IAdminSession getAdminSession();
    IUsers getModelUsers();
    IUserCounters getModelUserCounters();
    ISystemCounters getModelSystemCounters();
    IRecordSet getModelRecordSet();
    IEventLog getModelEventLog();
    void addEvent(String event);
    boolean isUserExist(String login);
    void addCounter(String counterType) throws AddCounterException;
    void deleteCounter(String counterType) throws AddCounterException;
}
