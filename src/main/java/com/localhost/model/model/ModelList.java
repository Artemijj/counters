package com.localhost.model.model;

import com.localhost.model.systemCounters.SystemCountersList;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.EventLogList;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.userCounters.IUserCounters;
import com.localhost.model.userCounters.UserCountersList;
import com.localhost.model.users.IUsers;
import com.localhost.model.records.RecordSetList;
import com.localhost.model.users.UsersList;

import java.sql.Connection;

public class ModelList implements IModel{
    private IUsers users;
    private IUserCounters userCounters;
    private ISystemCounters systemCounters;
    private IRecordSet recordSet;
    private IEventLog eventLog;

    public ModelList() {
        users = new UsersList();
        userCounters = new UserCountersList();
        systemCounters = new SystemCountersList();
        recordSet = new RecordSetList();
        eventLog = new EventLogList();
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

    @Override
    public Connection getCon() {
        return null;
    }
}
