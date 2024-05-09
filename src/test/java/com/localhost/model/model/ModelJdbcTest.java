package com.localhost.model.model;

import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.users.IUsers;
import org.junit.jupiter.api.*;

public class ModelJdbcTest {
    ModelList modelJdbc;

    @BeforeEach
    public void setUp() {
        modelJdbc = new ModelList();
    }

    @Test
    public void getUsersTest() {
        IUsers actualUsers = modelJdbc.getUsers();
        Assertions.assertInstanceOf(IUsers.class, actualUsers);
    }

    @Test
    public void getCountersTest() {
        ISystemCounters actualCounters = modelJdbc.getSystemCounters();
        Assertions.assertInstanceOf(ISystemCounters.class, actualCounters);
    }

    @Test
    public void getRecordSetTest() {
        IRecordSet actualRecordSet = modelJdbc.getRecordSet();
        Assertions.assertInstanceOf(IRecordSet.class, actualRecordSet);
    }

    @Test
    public void getEventLogTest() {
        IEventLog actualEventLog = modelJdbc.getEventLog();
        Assertions.assertInstanceOf(IEventLog.class, actualEventLog);
    }
}
