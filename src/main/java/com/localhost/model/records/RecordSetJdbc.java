package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.Record;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class RecordSetJdbc implements IRecordSet{
//    private DBCPDataSourceFactory dataSource;
    private Connection connection;

    public RecordSetJdbc(Connection connection) {
        this.connection = connection;
//        dataSource = new DBCPDataSourceFactory(fileProp);
    }

    @Override
    public ArrayList<Record> getRecordSetList() {
        ArrayList<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM counter.records";
        try (Statement stmt = connection.createStatement()) {
//            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
//                Record record = new Record(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("counter_type"), new CounterValue(new Date(resultSet.getTimestamp("date").getTime()), resultSet.getInt("value")));
                Record record = new Record(resultSet.getString("login"), resultSet.getString("counter_type"), new CounterValue(new Date(resultSet.getDate("date").getTime()), resultSet.getInt("value")));
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
//        int id = record.getId();
        String login = record.getLogin();
        String counterType = record.getCounterType();
//        Timestamp date = new Timestamp(record.getCounterValue().getDate().getTime());
        java.sql.Date date = new java.sql.Date(record.getCounterValue().getDate().getTime());
        int value = record.getCounterValue().getValue();
        String sql = "INSERT INTO counter.records (login, counter_type, date, value) VALUES (?, ?, ?, ?)";
        int result = 0;
        if (checkDate()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql);) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setInt(1, id);
                stmt.setString(1, login);
                stmt.setString(2, counterType);
                stmt.setDate(3, date);
                stmt.setInt(4, value);
                result = stmt.executeUpdate();
//            connection.close();
            } catch (SQLException e) {
                System.err.println("SQL error code - " + e.getErrorCode());
                System.err.println(e.getMessage());
//            throw new RuntimeException(e);
            }
        }
        return result == 1;
    }

    @Override
    public void deleteRecord(Record record) {
        int id = record.getId();
        String sql = "DELETE FROM counter.records WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.setInt(1, id);
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }

//    @Override
//    public int nextId() {
//        String sql = "SELECT id FROM records ORDER BY id DESC LIMIT 1";
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

    private boolean checkDate() {
        Date currentDate = new Date();
        int actualMonth = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        int actualYear = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        String sql = "SELECT count(*) FROM counter.records WHERE EXTRACT(MONTH FROM date) = ? AND EXTRACT(YEAR FROM date) = ?";
        int result = 1;
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, actualMonth);
            stmt.setInt(2, actualYear);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("count");
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return result == 0;
    }
}
