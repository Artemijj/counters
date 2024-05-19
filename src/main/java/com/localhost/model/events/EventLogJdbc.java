package com.localhost.model.events;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EventLogJdbc implements IEventLog{
    private DBCPDataSourceFactory dataSource;

    public EventLogJdbc(String fileProp) {
        dataSource = new DBCPDataSourceFactory(fileProp);
    }

    @Override
    public ArrayList<Event> getEventLogList() {
        ArrayList<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM counter.events";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
//                Event event = new Event(resultSet.getInt("id"), resultSet.getString("login"), new Date(resultSet.getTimestamp("date").getTime()), resultSet.getString("event"));
                Event event = new Event(resultSet.getString("login"), new Date(resultSet.getDate("date").getTime()), resultSet.getString("event"));
                events.add(event);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public boolean addEvent(Event event) {
//        int id = event.getId();
        String login = event.getLogin();
//        Timestamp date = new Timestamp(event.getDate().getTime());
        java.sql.Date date = new java.sql.Date(event.getDate().getTime());
        String txt = event.getActivity();
        String sql = "INSERT INTO counter.events (login, date, event) VALUES (?, ?, ?)";
        int result = 0;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, id);
            stmt.setString(1, login);
            stmt.setDate(2, date);
            stmt.setString(3, txt);
            result = stmt.executeUpdate();
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return result == 1;
    }

//    @Override
//    public int nextId() {
//        String sql = "SELECT id FROM events ORDER BY id DESC LIMIT 1";
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
