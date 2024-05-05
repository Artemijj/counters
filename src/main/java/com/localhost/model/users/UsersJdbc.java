package com.localhost.model.users;

import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class UsersJdbc implements IUsers {
//    private Connection connection;
//
//    public UsersJdbc() {
//        connection = DBCPDataSourceFactory.getConnection();
//    }

    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getBoolean("is_admin"));
                user.setFio(resultSet.getString("fio"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                users.add(user);
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        Boolean isAdmin = user.getIsAdmin();
        String fio = user.getFio();
        String phone = user.getPhoneNumber();
        String address = user.getAddress();
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setBoolean(3, isAdmin);
            stmt.setString(4, fio);
            stmt.setString(5, phone);
            stmt.setString(6, address);
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
    public void deleteUser(User user) {
        String login = user.getLogin();
        String sql = "DELETE FROM users WHERE login=?";
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

    @Override
    public User getUser(String login) {
        User user = null;
        String sql = "SELECT * FROM users WHERE login=?";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery(sql);
            user = new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getBoolean("is_admin"));
            user.setFio(resultSet.getString("fio"));
            user.setPhoneNumber(resultSet.getString("phone"));
            user.setAddress(resultSet.getString("address"));
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return user;
    }
}