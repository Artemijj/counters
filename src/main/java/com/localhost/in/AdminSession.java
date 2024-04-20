package com.localhost.in;

import com.localhost.model.*;

public class AdminSession implements IAdminSession{

    private IUserSession userSession;

    public AdminSession(IUserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void addUser(String login, String password) throws AdminException {
        if (!userSession.isUserExist(login)) {
            userSession.getModelUsers().addUser(new User(login, password, false));
        }
    }

    @Override
    public void addAdmin(String login, String password) throws AdminException {
        if (!userSession.isUserExist(login)) {
            userSession.getModelUsers().addUser(new User(login, password, true));
        }
    }

    @Override
    public void removeUser(String login) throws AdminException {
        User user = userSession.getModelUsers().getUser(login);
        userSession.getModelUsers().deleteUser(user);
    }

    @Override
    public User[] getAllUsers() throws AdminException {
        return userSession.getModelUsers().getUserList().toArray(new User[0]);
    }

    @Override
    public User getUserData(String login) throws AdminException {
        return userSession.getModelUsers().getUser(login);
    }

    @Override
    public void setPassword(String login, String password) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).setPassword(password);
        }
    }

    @Override
    public void setAddress(String login, String address) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).setAddress(address);
        }
    }

    @Override
    public void setPhone(String login, String phone) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).setPhoneNumber(phone);
        }
    }

    @Override
    public void createCounter(String counterName) throws AdminException {
        userSession.getModelCounters().addCounter(new CounterType(counterName));
    }

    @Override
    public CounterType[] getAllSystemCounters() {
        return userSession.getModelCounters().getCounterList().toArray(new CounterType[0]);
    }

    @Override
    public CounterType[] getUserCounters(String login) throws AdminException {
        return userSession.getModelUsers().getUser(login).getUserCounters().toArray(new CounterType[0]);
    }

    @Override
    public void linkCounter(String login, CounterType counter) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).addCounter(counter);
        }
    }

    @Override
    public void unlinkCounter(String login, CounterType counter) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).deleteCounter(counter);
        }
    }

    @Override
    public CounterValue[] getCounterValues(String login, CounterType counter) throws AdminException {
        return userSession.getModelRecordSet().getRecordSetList().stream()
//                .filter(reading -> reading.getLogin().equals(userSession.getModelUsers().getUser(login)))
                .filter(reading -> reading.getLogin().equals(userSession.getLogin()))
                .filter(reading -> reading.getCounterType().equals(counter.getCounterTypeName()))
                .map(reading -> reading.getCounterValue())
                .toArray(CounterValue[]::new);
    }

    @Override
    public Event[] getUserActivities(String login) throws AdminException {
        return userSession.getModelEventLog().getEventLog().stream()
                .filter(event -> event.getLogin().equals(login)).toArray(Event[]::new);
    }

    @Override
    public void setFio(String login, String fio) throws AdminException {
        if (userSession.isUserExist(login)) {
            userSession.getModelUsers().getUser(login).setFio(fio);
        }
    }
}
