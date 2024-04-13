package com.localhost.in;

import com.localhost.model.*;
import com.localhost.model.counters.ICounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
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

    default CounterType[] userCounters() throws AdminException {
        return getAdminSession().getUserCounters(getLogin());
    }

    default CounterValue[] getCounterValues(CounterType counter) throws AdminException {
        return getAdminSession().getCounterValues(getLogin(), counter);
    }

    CounterValue getLastValue(CounterType counter);
    void addCounterValue(CounterType counter, CounterValue value) throws AddCounterException;
    IAdminSession getAdminSession();
    IUsers getModelUsers();
    ICounters getModelCounters();
    IRecordSet getModelRecordSet();
    IEventLog getModelEventLog();
    void addEvent(String event);
    boolean isUserExist(String login);
}
