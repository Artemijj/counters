package com.localhost.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputOutput implements IInputOutput{
    @Override
    public String get() {
        String inputString;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            inputString = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputString;
    }

    @Override
    public void put(String message) {
        System.out.println(message);
    }
}
