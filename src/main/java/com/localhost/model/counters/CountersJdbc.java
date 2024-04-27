package com.localhost.model.counters;

import com.localhost.model.CounterType;
import com.localhost.model.DBCPDataSourceFactory;

import java.sql.*;
import java.util.ArrayList;

public class CountersJdbc implements ICounters {
    private Connection connection;
    private PreparedStatement stmt;
    private ResultSet resultSet = null;
    private String sql = "";

    public CountersJdbc() {
        connection = DBCPDataSourceFactory.getConnection();
        try {
            stmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<CounterType> getCounterList() {
        ArrayList<CounterType> counterTypes = new ArrayList<>();
        sql = "SELECT * FROM counters";
        try {
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                CounterType counterType = new CounterType(resultSet.getString("counter_type"));
                counterTypes.add(counterType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counterTypes;
    }

    @Override
    public boolean addCounter(CounterType counterType) {
        String counterName = counterType.getCounterTypeName();
        sql = "INSERT INTO counters VALUES (?)";
        int result;
        try {
            result = stmt.executeUpdate(sql);
            stmt.setString(1, counterName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result == 1;
    }

    @Override
    public void deleteCounter(CounterType counterType) {
        String counterName = counterType.getCounterTypeName();
        sql = "DELETE FROM counters WHERE counter_type=?";
        try {
            stmt.executeUpdate(sql);
            stmt.setString(1, counterName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CounterType getCounter(String counter) {
        CounterType counterType;
        sql = "SELECT * FROM counters WHERE counter_type=?";
        try {
            resultSet = stmt.executeQuery(sql);
            stmt.setString(1, counter);
            counterType = new CounterType(resultSet.getString("counter_type"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counterType;
    }
}
