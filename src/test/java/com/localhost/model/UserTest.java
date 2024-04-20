package com.localhost.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    private User user;
    @Mock
    User userMock;
    @Mock
    CounterType counterType;

    @Test
    public void setLoginTest() {
        userMock.setLogin("newName");
        userMock.setLogin("newName");
        Mockito.verify(userMock, Mockito.times(2)).setLogin("newName");
    }

    @BeforeEach
    public void setUp() {
        user = new User("name", "passwd", false);
    }

    @Test
    public void getLoginTest() {
        String expectedLogin = "name";
        String actualLogin = user.getLogin();
        Assertions.assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void getAddressTest() {
        user.setAddress("addr");
        String expectedAddress = "addr";
        String actualAddress = user.getAddress();
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void setAddressTest() {
        String expectedAddress = "newAddr";
        user.setAddress("newAddr");
        String actualAddress = user.getAddress();
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void getPhoneNumberTest() {
        user.setPhoneNumber("+7123456");
        String expectedPhone = "+7123456";
        String actualPhone = user.getPhoneNumber();
        Assertions.assertEquals(expectedPhone, actualPhone);
    }

    @Test
    public void setPhoneNumberTest() {
        String expectedPhone = "+7654321";
        user.setPhoneNumber("+7654321");
        String actualPhone = user.getPhoneNumber();
        Assertions.assertEquals(expectedPhone, actualPhone);
    }

    @Test
    public void getPasswordTest() {
        String expectedPassword = "passwd";
        String actualPassword = user.getPassword();
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void setPasswordTest() {
        String expectedPassword = "newPasswd";
        user.setPassword("newPasswd");
        String actualPassword = user.getPassword();
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void addCounterTest() {
        int expectedNumber = 1;
        user.addCounter(counterType);
        int actualNumber = user.getUserCounters().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void deleteCounterTest() {
        int expectedNumber = 0;
        user.addCounter(counterType);
        user.deleteCounter(counterType);
        int actualNumber = user.getUserCounters().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserCountersTest() {
        int expectedNumber = 0;
        int actualNumber = user.getUserCounters().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getIsAdminTest() {
        boolean actual = user.getIsAdmin();
        Assertions.assertFalse(actual);
    }

    @Test
    public void getFioTest() {
        String expectedFio = "Иван Иванович Иванов";
        user.setFio("Иван Иванович Иванов");
        String actualFio = user.getFio();
        Assertions.assertEquals(expectedFio, actualFio);
    }
}
