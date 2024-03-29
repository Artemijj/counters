package com.localhost.in;

import com.localhost.model.*;

public class AdminSession implements IAdminSession{

    private IUserSession userSession;
    private IUsers thisUsers = userSession.getModel().getUsers();
    private ICounters thisCounters = userSession.getModel().getCounters();
    private IRecordSet thisRecordSet = userSession.getModel().getRecordSet();

    public AdminSession(IUserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void addUser(String login, String password) throws AdminException {
        thisUsers.addUser(new User(login, password, null, null, false));
    }

    @Override
    public void addAdmin(String login, String password) throws AdminException {
        thisUsers.addUser(new User(login, password, null, null, true));
    }

    @Override
    public void removeUser(String login) throws AdminException {
        User user = thisUsers.getUser(login);
        thisUsers.deleteUser(user);
    }

    @Override
    public User[] getAllUsers() throws AdminException {
        return thisUsers.getUserList().toArray(new User[0]);
    }

    @Override
    public User getUserData(String login) throws AdminException {
        return thisUsers.getUser(login);
    }

    @Override
    public void SetPassword(String login, String password) throws AdminException {
        thisUsers.getUser(login).setPassword(password);
    }

    @Override
    public void setAddress(String login, String address) throws AdminException {
        thisUsers.getUser(login).setAddress(address);
    }

    @Override
    public void setPhone(String login, String phone) throws AdminException {
        thisUsers.getUser(login).setPhoneNumber(phone);
    }

    @Override
    public void createCounter(String counterName) throws AdminException {
        thisCounters.addCounter(new CounterType(counterName));
    }

    @Override
    public CounterType[] getAllSystemCounters() {
        return thisCounters.getCounterList().toArray(new CounterType[0]);
    }

    @Override
    public CounterType[] getUserCounters(String login) throws AdminException {
        return thisUsers.getUser(login).getUserCounters().toArray(new CounterType[0]);
    }

    @Override
    public void linkCounter(String login, CounterType counter) throws AdminException {
        thisUsers.getUser(login).addCounter(counter);
    }

    @Override
    public void unlinkCounter(String login, CounterType counter) throws AdminException {
        thisUsers.getUser(login).deleteCounter(counter);
    }

    @Override
    public CounterValue[] getCounterValues(String login, CounterType counter) throws AdminException {
        return (CounterValue[]) thisRecordSet.getRecordSetList().stream()
                .filter(reading -> reading.getUser().equals(thisUsers.getUser(login)))
                .filter(reading -> reading.getCounterType().equals(counter))
                .map(reading -> reading.getCounterValue())
                .toArray();
    }
}
