package com.localhost.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputOutput implements IInputOutput{
    @Override
    public String get() {
        String inputString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            inputString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            put("Some wrong...");
        }
        return inputString;
    }

    @Override
    public void put(String message) {
        System.out.println(message);
    }
}
