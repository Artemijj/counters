package com.localhost.model.users;

import com.localhost.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UsersJdbc implements IUsers {
//    private DBCPDataSourceFactory dataSource;
    private Connection connection;

    public UsersJdbc(Connection connection) {
        this.connection = this.connection;
//        dataSource = new DBCPDataSourceFactory(fileProp);
    }

    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM counter.users";
        try (Statement stmt = connection.createStatement()) {
//            Statement stmt = connection.createStatement();
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
        String sql = "INSERT INTO counter.users VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setBoolean(3, isAdmin);
            stmt.setString(4, fio);
            stmt.setString(5, address);
            stmt.setString(6, phone);
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
        String sql = "DELETE FROM counter.users WHERE login=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.executeUpdate();
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
        String sql = "SELECT * FROM counter.users WHERE login=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            user = new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getBoolean("is_admin"));
            user.setFio(resultSet.getString("fio"));
            user.setPhoneNumber(resultSet.getString("phone"));
            user.setAddress(resultSet.getString("address"));
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        Boolean isAdmin = user.getIsAdmin();
        String fio = user.getFio();
        String phone = user.getPhoneNumber();
        String address = user.getAddress();
        String sql = "UPDATE counter.users SET password = ?, is_admin = ?, fio = ?, address = ?, phone = ? WHERE login = ?";
//        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(6, login);
            stmt.setString(1, password);
            stmt.setBoolean(2, isAdmin);
            stmt.setString(3, fio);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.executeUpdate();
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }
}
