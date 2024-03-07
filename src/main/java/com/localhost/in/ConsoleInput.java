package com.localhost.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
    public String input() {
        String inputString;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            inputString = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputString;
    }
}