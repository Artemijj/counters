package com.localhost.model.model;

import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.userCounters.IUserCounters;
import com.localhost.model.users.IUsers;

import java.sql.Connection;

public interface IModel {
    IUsers getUsers();
    IUserCounters getUserCounters();
    ISystemCounters getSystemCounters();
    IRecordSet getRecordSet();
    IEventLog getEventLog();
    Connection getCon();
}
