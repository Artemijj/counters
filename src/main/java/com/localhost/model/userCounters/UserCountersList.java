package com.localhost.model.userCounters;

import com.localhost.model.UserCounter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class UserCountersList implements IUserCounters{
    private ArrayList<UserCounter> userCounters;

    public UserCountersList() {
        userCounters = new ArrayList<>();
    }

    @Override
    public ArrayList<UserCounter> getUserCountersList() {
        return userCounters;
    }

    @Override
    public ArrayList<UserCounter> getUserCountersListByUser(String login) {
        return (ArrayList<UserCounter>) userCounters.stream()
                .filter(uC -> uC.getLogin().equals(login))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addUserCounter(UserCounter userCounter) {
        boolean answer = false;
        long userCounterCount = userCounters.stream()
                .filter(uC -> uC.getLogin().equals(userCounter.getLogin()))
                .filter(uC -> uC.getCounterName().equals(userCounter.getCounterName()))
                .count();
        if ((userCounterCount == 0) && (!userCounters.contains(userCounter))) {
            userCounters.add(userCounter);
            answer = true;
        }
        return answer;
    }

    @Override
    public void deleteUserCounter(UserCounter userCounter) {
        userCounters.remove(userCounter);
    }

    @Override
    public int nextId() {
        return (userCounters.size() == 0) ? 1 : userCounters.stream().map(UserCounter::getId).max(Comparator.naturalOrder()).get() + 1;
    }
}
