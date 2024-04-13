package com.localhost.model;

import com.localhost.in.AdminException;

public class Tools {
    public static int parse(String string) {
        int selectedNumber = Integer.MAX_VALUE;
        try {
            selectedNumber = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Введённый символ не является числом.");
        }
        return selectedNumber;
    }
}
