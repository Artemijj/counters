package com.localhost.model;

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
