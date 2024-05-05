package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class RecordSetJdbc implements IRecordSet{
//    private Connection connection;
//
//    public RecordSetJdbc() {
//        connection = DBCPDataSourceFactory.getConnection();
//    }

    @Override
    public ArrayList<Record> getRecordSetList() {
        ArrayList<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM records";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Record record = new Record(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("counter_type"), new CounterValue(new Date(resultSet.getTimestamp("date").getTime()), resultSet.getInt("value")));
                records.add(record);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return records;
    }

    @Override
    public boolean addRecord(Record record) {
        int id = nextId();
        String login = record.getLogin();
        String counterType = record.getCounterType();
        Timestamp date = new Timestamp(record.getCounterValue().getDate().getTime());
        int value = record.getCounterValue().getValue();
        String sql = "INSERT INTO records VALUES (?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, login);
            stmt.setString(3, counterType);
            stmt.setTimestamp(4, date);
            stmt.setInt(5, value);
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
    public int nextId() {
        String sql = "SELECT id FROM records ORDER BY id DESC LIMIT 1";
        int id = 0;
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            id = resultSet.getInt("id");
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return ++id;
    }

    private boolean checkDate() {
        String sql = "SELECT date FROM records";
        return false;
    }
}
