package com.localhost.model.model;

import com.localhost.model.model.ModelList;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelListTest {
    ModelList modelList;

    @BeforeEach
    public void setUp() {
        modelList = new ModelList();
    }

    @Test
    public void getUsersTest() {
        IUsers actualUsers = modelList.getUsers();
        Assertions.assertInstanceOf(IUsers.class, actualUsers);
    }

    @Test
    public void getCountersTest() {
        ISystemCounters actualCounters = modelList.getSystemCounters();
        Assertions.assertInstanceOf(ISystemCounters.class, actualCounters);
    }

    @Test
    public void getRecordSetTest() {
        IRecordSet actualRecordSet = modelList.getRecordSet();
        Assertions.assertInstanceOf(IRecordSet.class, actualRecordSet);
    }

    @Test
    public void getEventLogTest() {
        IEventLog actualEventLog = modelList.getEventLog();
        Assertions.assertInstanceOf(IEventLog.class, actualEventLog);
    }
}
