package com.localhost.model.users;

import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersJdbc implements IUsers{
//    private DataSource dataSource;
    private Connection connection;
    private Statement stmt;
    private ResultSet resultSet = null;

    public UsersJdbc() {
//        dataSource = DBCPDataSourceFactory.getDataSource();
        connection = DBCPDataSourceFactory.getConnection();
        try {
//            connection = dataSource.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> getUserList() {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            resultSet = stmt.executeQuery("INSERT INTO users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getUser(String login) {
        return null;
    }
}
