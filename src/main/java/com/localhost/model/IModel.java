package com.localhost.model;

public interface IModel {
    IUsers getUsers();
    ICounters getCounters();
    IRecordSet getRecordSet();
    IEventLog getEventLog();
}
