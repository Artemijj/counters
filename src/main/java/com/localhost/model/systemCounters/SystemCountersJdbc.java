package com.localhost.model.systemCounters;

import com.localhost.model.CounterType;
import com.localhost.model.DBCPDataSourceFactory;

import java.sql.*;
import java.util.ArrayList;

public class SystemCountersJdbc implements ISystemCounters {
//    private Connection connection;
//    private PreparedStatement stmt;
//    private ResultSet resultSet = null;
//    private String sql = "";

//    public SystemCountersJdbc() {
//        connection = DBCPDataSourceFactory.getConnection();
//        try {
//            stmt = connection.prepareStatement(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public ArrayList<CounterType> getCounterList() {
        ArrayList<CounterType> counterTypes = new ArrayList<>();
        String sql = "SELECT * FROM counters";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                CounterType counterType = new CounterType(resultSet.getString("counter_type"));
                counterTypes.add(counterType);
            }
//            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counterTypes;
    }

    @Override
    public boolean addCounter(CounterType counterType) {
        String counterName = counterType.getCounterTypeName();
        String sql = "INSERT INTO counters VALUES (?)";
        int result;
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            result = stmt.executeUpdate();
            stmt.setString(1, counterName);
//            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result == 1;
    }

    @Override
    public void deleteCounter(CounterType counterType) {
        String counterName = counterType.getCounterTypeName();
        String sql = "DELETE FROM counters WHERE counter_type=?";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.setString(1, counterName);
//            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CounterType getCounter(String counter) {
        CounterType counterType;
        String sql = "SELECT * FROM counters WHERE counter_type=?";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            stmt.setString(1, counter);
            counterType = new CounterType(resultSet.getString("counter_type"));
//            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counterType;
    }
}
