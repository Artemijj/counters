package com.localhost.model.userCounters;

import com.localhost.model.User;
import com.localhost.model.UserCounter;
import org.junit.jupiter.api.*;

public class UserCountersListTest {
    private IUserCounters userCountersList;
    private User user = new User("name", "pass", false);
    private String counterName = "counterName";

    private UserCounter userCounter;

    @BeforeEach
    public void setUp() {
        userCountersList = new UserCountersList();
        userCounter = new UserCounter("name", counterName);
    }

    @Test
    public void getUserCountersListByUserTest() {
        int expectedNumber = 1;
        userCountersList.addUserCounter(userCounter);
        int actualNumber = userCountersList.getUserCountersListByUser("name").size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserCounterTest() {
        int expectedNumber = 1;
        userCountersList.addUserCounter(userCounter);
        int actualNumber = userCountersList.getUserCountersList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void deleteUserCounterTest() {
        int expectedNumber = 0;
        userCountersList.addUserCounter(userCounter);
        userCountersList.deleteUserCounter(userCounter);
        int actualNumber = userCountersList.getUserCountersList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }
}
