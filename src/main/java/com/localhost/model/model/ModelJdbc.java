package com.localhost.model.model;

import com.localhost.model.events.EventLogJdbc;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.records.RecordSetJdbc;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.systemCounters.SystemCountersJdbc;
import com.localhost.model.userCounters.IUserCounters;
import com.localhost.model.userCounters.UserCountersJdbc;
import com.localhost.model.users.IUsers;
import com.localhost.model.users.UsersJdbc;

public class ModelJdbc implements IModel{
    private IUsers users;
    private IUserCounters userCounters;
    private ISystemCounters systemCounters;
    private IRecordSet recordSet;
    private IEventLog eventLog;

    public ModelJdbc() {
        users = new UsersJdbc();
        userCounters = new UserCountersJdbc();
        systemCounters = new SystemCountersJdbc();
        recordSet = new RecordSetJdbc();
        eventLog = new EventLogJdbc();
    }

    @Override
    public IUsers getUsers() {
        return users;
    }

    @Override
    public IUserCounters getUserCounters() {
        return userCounters;
    }

    @Override
    public ISystemCounters getSystemCounters() {
        return systemCounters;
    }

    @Override
    public IRecordSet getRecordSet() {
        return recordSet;
    }

    @Override
    public IEventLog getEventLog() {
        return eventLog;
    }
}
