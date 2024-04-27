package com.localhost.model.events;

import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EventLogJdbc implements IEventLog{
    private Connection connection;
    private PreparedStatement stmt;
    private ResultSet resultSet = null;
    private String sql = "";

    public EventLogJdbc() {
        connection = DBCPDataSourceFactory.getConnection();
        try {
            stmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Event> getEventLog() {
        ArrayList<Event> events = new ArrayList<>();
        sql = "SELECT * FROM events";
        try {
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Event event = new Event(resultSet.getInt("id"), resultSet.getString("login"), new Date(resultSet.getTimestamp("date").getTime()), resultSet.getString("event"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public boolean addEvent(Event event) {
        String login = event.getLogin();
        Timestamp date = new Timestamp(event.getDate().getTime());
        String txt = event.getActivity();
        sql = "INSERT INTO events VALUES (?, ?, ?)";
        int result;
        try {
            result = stmt.executeUpdate(sql);
            stmt.setString(1, login);
            stmt.setTimestamp(2, date);
            stmt.setString(3, txt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result == 1;
    }

    @Override
    public int nextId() {
        return 0;
    }
}
