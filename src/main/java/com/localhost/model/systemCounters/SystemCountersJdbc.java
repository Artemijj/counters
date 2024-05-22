package com.localhost.model.systemCounters;

import java.sql.*;
import java.util.ArrayList;

public class SystemCountersJdbc implements ISystemCounters {
//    private DBCPDataSourceFactory dataSource;
    private Connection connection;

    public SystemCountersJdbc(Connection connection) {
        this.connection = this.connection;
//        dataSource = new DBCPDataSourceFactory(fileProp);
    }

    @Override
    public ArrayList<String> getCounterList() {
        ArrayList<String> counterTypes = new ArrayList<>();
        String sql = "SELECT * FROM counter.system_counters";
        try (Statement stmt = connection.createStatement()) {
//            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String counterType = resultSet.getString("counter_type");
                counterTypes.add(counterType);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return counterTypes;
    }

    @Override
    public boolean addCounter(String counterType) {
//        String counterName = counterType.getCounterTypeName();
        String sql = "INSERT INTO counter.system_counters VALUES (?)";
        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, counterType);
            result = stmt.executeUpdate();
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return result == 1;
    }

    @Override
    public void deleteCounter(String counterType) {
//        String counterName = counterType.getCounterTypeName();
        String sql = "DELETE FROM counter.system_counters WHERE counter_type=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, counterType);
            stmt.executeUpdate();
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }

//    @Override
//    public CounterType getCounter(String counter) {
//        CounterType counterType = null;
//        String sql = "SELECT * FROM counters WHERE counter_type=?";
//        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet resultSet = stmt.executeQuery();
//            stmt.setString(1, counter);
//            counterType = new CounterType(resultSet.getString("counter_type"));
////            connection.close();
//        } catch (SQLException e) {
//            System.err.println("SQL error code - " + e.getErrorCode());
//            System.err.println(e.getMessage());
////            throw new RuntimeException(e);
//        }
//        return counterType;
//    }
}
