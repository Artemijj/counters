package com.localhost.in;

import com.localhost.model.*;

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
    IModel getModel();
    void addEvent(String event);
}
