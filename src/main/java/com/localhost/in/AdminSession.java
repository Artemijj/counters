package com.localhost.in;

import com.localhost.model.*;

import java.util.Objects;

public class AdminSession implements IAdminSession {

    private IUserSession userSession;

    public AdminSession(IUserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void addUser(String login, String password) throws AdminException {
        if (!userSession.isUserExist(login)) {
            String hash = Tools.getMD5Hash(password);
            userSession.getModelUsers().addUser(new User(login, hash, false));
        }
    }

    @Override
    public void addAdmin(String login, String password) throws AdminException {
        if (!userSession.isUserExist(login)) {
            String hash = Tools.getMD5Hash(password);
            userSession.getModelUsers().addUser(new User(login, hash, true));
        }
    }

    @Override
    public void removeUser(String login) throws AdminException {
        if (userSession.isUserExist(login)) {
            User user = userSession.getModelUsers().getUser(login);
            userSession.getModelUsers().deleteUser(user);
        }
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
            User user = userSession.getModelUsers().getUser(login);
            String hash = Tools.getMD5Hash(password);
            user.setPassword(hash);
            userSession.getModelUsers().updateUser(user);
        }
    }

    @Override
    public void setFio(String login, String fio) throws AdminException {
        if (userSession.isUserExist(login)) {
            User user = userSession.getModelUsers().getUser(login);
            user.setFio(fio);
            userSession.getModelUsers().updateUser(user);
        }
    }

    @Override
    public void setAddress(String login, String address) throws AdminException {
        if (userSession.isUserExist(login)) {
            User user = userSession.getModelUsers().getUser(login);
            user.setAddress(address);
            userSession.getModelUsers().updateUser(user);
        }
    }

    @Override
    public void setPhone(String login, String phone) throws AdminException {
        if (userSession.isUserExist(login)) {
            User user = userSession.getModelUsers().getUser(login);
            user.setPhoneNumber(phone);
            userSession.getModelUsers().updateUser(user);
        }
    }

    @Override
    public void createCounter(String counterName) throws AdminException {
        userSession.getModelSystemCounters().addCounter(counterName);
    }

    @Override
    public String[] getAllSystemCounters() {
        return userSession.getModelSystemCounters().getCounterList().toArray(new String[0]);
    }

    @Override
    public String[] getUserCounters(String login) throws AdminException {
        return userSession.getModelUserCounters().getUserCountersListByUser(login)
                .stream()
                .map(userCounter -> userCounter.getCounterName())
//                .map(name -> new CounterType(name))
                .toArray(String[]::new);
//                .toArray(new CounterType[0]);
    }

    @Override
    public void linkCounter(String login, String counter) throws AdminException {
        if (userSession.isUserExist(login)) {
//            userSession.getModelUsers().getUser(login).addCounter(counter);
            userSession.getModelUserCounters().addUserCounter(new UserCounter(login, counter));
        }
    }

    @Override
    public void unlinkCounter(String login, String counter) throws AdminException {
        if (userSession.isUserExist(login)) {
//            userSession.getModelUsers().getUser(login).deleteCounter(counter);
            UserCounter userCounter = userSession.getModelUserCounters().getUserCountersList().stream()
                    .filter(uC -> uC.getLogin().equals(login))
                    .filter(uC -> uC.getCounterName().equals(counter))
                    .findFirst().orElse(null);
            if (!Objects.isNull(userCounter)) {
                userSession.getModelUserCounters().deleteUserCounter(userCounter);
            }
        }
    }

    @Override
    public CounterValue[] getCounterValues(String login, String counter) throws AdminException {
        return userSession.getModelRecordSet().getRecordSetList().stream()
//                .filter(reading -> reading.getLogin().equals(userSession.getModelUsers().getUser(login)))
                .filter(reading -> reading.getLogin().equals(userSession.getLogin()))
                .filter(reading -> reading.getCounterType().equals(counter))
                .map(reading -> reading.getCounterValue())
                .toArray(CounterValue[]::new);
    }

    @Override
    public Event[] getUserActivities(String login) throws AdminException {
        return userSession.getModelEventLog().getEventLogList().stream()
                .filter(event -> event.getLogin().equals(login)).toArray(Event[]::new);
    }
}
