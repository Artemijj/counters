package com.localhost.model;

import com.localhost.model.counters.ICounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ModelTest {
    Model model;

    @BeforeEach
    public void setUp() {
        model = new Model();
    }

    @Test
    public void getUsersTest() {
        IUsers actualUsers = model.getUsers();
        Assertions.assertInstanceOf(IUsers.class, actualUsers);
    }

    @Test
    public void getCountersTest() {
        ICounters actualCounters = model.getCounters();
        Assertions.assertInstanceOf(ICounters.class, actualCounters);
    }

    @Test
    public void getRecordSetTest() {
        IRecordSet actualRecordSet = model.getRecordSet();
        Assertions.assertInstanceOf(IRecordSet.class, actualRecordSet);
    }

    @Test
    public void getEventLogTest() {
        IEventLog actualEventLog = model.getEventLog();
        Assertions.assertInstanceOf(IEventLog.class, actualEventLog);
    }
}
