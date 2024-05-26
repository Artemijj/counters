package com.localhost.model.model;

import com.localhost.model.DBCPDataSourceFactory;
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
import java.sql.Connection;
import java.util.Properties;

public class ModelJdbc implements IModel{
    private DBCPDataSourceFactory dataSource;
    private Properties properties = new Properties();
    private IUsers users;
    private IUserCounters userCounters;
    private ISystemCounters systemCounters;
    private IRecordSet recordSet;
    private IEventLog eventLog;
    private Connection connection;

    public ModelJdbc() {
        try {
            properties.load(new FileInputStream("./src/main/resources/file.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource = new DBCPDataSourceFactory(properties.getProperty("appProp"));

        connection = dataSource.getConnection();
        users = new UsersJdbc(connection);
        userCounters = new UserCountersJdbc(connection);
        systemCounters = new SystemCountersJdbc(connection);
        recordSet = new RecordSetJdbc(connection);
        eventLog = new EventLogJdbc(connection);
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
        return connection;
    }
}
