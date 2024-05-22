package com.localhost.model.model;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestModelJdbc implements IModel{
    private DBCPDataSourceFactory dataSource;
    private Properties properties = new Properties();
    private IUsers users;
    private IUserCounters userCounters;
    private ISystemCounters systemCounters;
    private IRecordSet recordSet;
    private IEventLog eventLog;

    public TestModelJdbc() {
        try {
            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource = new DBCPDataSourceFactory(properties.getProperty("appPropTest"));
        System.out.println("TestModelJdbc " + properties.getProperty("appPropTest"));//!!!!!!!!!!!!!!

        users = new UsersJdbc(dataSource.getConnection());
        userCounters = new UserCountersJdbc(dataSource.getConnection());
        systemCounters = new SystemCountersJdbc(dataSource.getConnection());
        recordSet = new RecordSetJdbc(dataSource.getConnection());
        eventLog = new EventLogJdbc(dataSource.getConnection());
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
    public DBCPDataSourceFactory getDataSource() {
        return dataSource;
    }
}
