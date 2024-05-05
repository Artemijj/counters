package com.localhost.model.userCounters;

import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.UserCounter;

import java.sql.*;
import java.util.ArrayList;

public class UserCountersJdbc implements IUserCounters{
//    private Connection connection;
//
//    public UserCountersJdbc() {
//        connection = DBCPDataSourceFactory.getConnection();
//    }

    @Override
    public ArrayList<UserCounter> getUserCountersList() {
        ArrayList<UserCounter> userCounters = new ArrayList<>();
        String sql = "SELECT * FROM user_counters";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                UserCounter userCounter = new UserCounter(resultSet.getString("login"), resultSet.getString("counter_name"));
                userCounters.add(userCounter);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return userCounters;
    }

    @Override
    public ArrayList<UserCounter> getUserCountersListByUser(String login) {
        ArrayList<UserCounter> userCounters = new ArrayList<>();
        String sql = "SELECT * FROM user_counters WHERE login=?";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UserCounter userCounter = new UserCounter(resultSet.getString("login"), resultSet.getString("counter_name"));
                userCounters.add(userCounter);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return userCounters;
    }

    @Override
    public boolean addUserCounter(UserCounter userCounter) {
//        int id = nextId();
        String login = userCounter.getLogin();
        String counterName = userCounter.getCounterName();
        String sql = "INSERT INTO events VALUES (?, ?)";
        int result = 0;
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, id);
            stmt.setString(1, login);
            stmt.setString(2, counterName);
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
    public void deleteUserCounter(UserCounter userCounter) {
        String login = userCounter.getLogin();
        String sql = "DELETE FROM user_counters WHERE login=?";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.setString(1, login);
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }


//    @Override
//    public int nextId() {
//        String sql = "SELECT id FROM user_counters ORDER BY id DESC LIMIT 1";
//        int id = 0;
//        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
//            Statement stmt = connection.createStatement();
//            ResultSet resultSet = stmt.executeQuery(sql);
//            id = resultSet.getInt("id");
////            connection.close();
//        } catch (SQLException e) {
//            System.err.println("SQL error code - " + e.getErrorCode());
//            System.err.println(e.getMessage());
////            throw new RuntimeException(e);
//        }
//        return ++id;
//    }
}
