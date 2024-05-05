package com.localhost.model.userCounters;

import com.localhost.model.UserCounter;

import java.util.ArrayList;

public interface IUserCounters {
    ArrayList<UserCounter> getUserCountersList();
    ArrayList<UserCounter> getUserCountersListByUser(String login);
    boolean addUserCounter(UserCounter userCounter);
    void deleteUserCounter(UserCounter userCounter);
//    int nextId();
}
