package com.localhost.model;

import com.localhost.model.counters.Counters;
import com.localhost.model.counters.ICounters;
import com.localhost.model.events.EventLog;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;
import com.localhost.model.records.RecordSet;
import com.localhost.model.users.Users;

public class Model implements IModel{
    private IUsers users;
    private ICounters counters;
    private IRecordSet recordSet;
    private IEventLog eventLog;

    public Model() {
        users = new Users();
        counters = new Counters();
        recordSet = new RecordSet();
        eventLog = new EventLog();
    }


    @Override
    public IUsers getUsers() {
        return users;
    }

    @Override
    public ICounters getCounters() {
        return counters;
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
