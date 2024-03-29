package com.localhost.model;

public class Model implements IModel{
    private IUsers users;
    private ICounters counters;
    private IRecordSet recordSet;

    public Model() {
        users = new Users();
        counters = new Counters();
        recordSet = new RecordSet();
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
}
