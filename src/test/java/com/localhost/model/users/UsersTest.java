package com.localhost.model.users;

import com.localhost.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersTest {
    private IUsers users;

    private User user = new User("name", "pass", false);

    @BeforeEach
    public void setUp() {
        users = new Users();
    }

    @Test
    public void getUserListTest() {
        int expectedNumber = 0;
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserTrueTest() {
        boolean actual = users.addUser(user);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addUserFalseTest() {
        users.addUser(user);
        boolean actual = users.addUser(user);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteUserTest() {
        users.addUser(user);
        users.deleteUser(user);
        int expectedNumber = 0;
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserTest() {
        users.addUser(user);
        User actualUser = users.getUser("name");
        Assertions.assertInstanceOf(User.class, actualUser);
    }
}
