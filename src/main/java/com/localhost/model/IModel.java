package com.localhost.model;

import com.localhost.model.counters.ICounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;

public interface IModel {
    IUsers getUsers();
    ICounters getCounters();
    IRecordSet getRecordSet();
    IEventLog getEventLog();
}
