package com.localhost.in;

public class AdminException extends Exception{

    public AdminException(String message) {
        super("User is not admin!");
    }
}
