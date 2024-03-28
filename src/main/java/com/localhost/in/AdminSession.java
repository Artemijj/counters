package com.localhost.in;

import com.localhost.model.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminSession implements IAdminSession{
    @Override
    public void addUser(String login, String password) throws AdminException {
        Users.addUser(new User(login, password, null, null));
    }

    @Override
    public void removeUser(String login) throws AdminException {
        User user = Users.getUser(login);
        Users.deleteUser(user);
    }

    @Override
    public User[] getAllUsers() throws AdminException {
        return Users.getUsers().toArray(new User[0]);
    }

    @Override
    public User getUserData(String login) throws AdminException {
        return Users.getUser(login);
    }

    @Override
    public void SetPassword(String login, String password) throws AdminException {
        Users.getUser(login).setPassword(password);
    }

    @Override
    public void setAddress(String login, String address) throws AdminException {
        Users.getUser(login).setAddress(address);
    }

    @Override
    public void setPhone(String login, String phone) throws AdminException {
        Users.getUser(login).setPhoneNumber(phone);
    }

    @Override
    public void createCounter(String counterName) throws AdminException {
        Counters.addCounter(new CounterType(counterName));
    }

    @Override
    public CounterType[] getAllSystemCounters() {
        return Counters.getCounters().toArray(new CounterType[0]);
    }

    @Override
    public CounterType[] getUserCounters(String login) throws AdminException {
        return Users.getUser(login).getUserCounters().toArray(new CounterType[0]);
    }

    @Override
    public void linkCounter(String login, CounterType counter) throws AdminException {
        Users.getUser(login).addCounter(counter);
    }

    @Override
    public void unlinkCounter(String login, CounterType counter) throws AdminException {
        Users.getUser(login).deleteCounter(counter);
    }

    @Override
    public CounterValue[] getCounterValues(String login, CounterType counter) throws AdminException {
        return (CounterValue[]) Readings.getReadings().stream()
                .filter(reading -> reading.getUser().equals(Users.getUser(login)))
                .filter(reading -> reading.getCounterType().equals(counter))
                .map(reading -> reading.getCounterValue())
                .toArray();
    }
}
