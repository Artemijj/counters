package com.localhost.model;

public class Tools {
    public static int parse(String string) {
        int selectedNumber = Integer.MAX_VALUE;
        try {
            selectedNumber = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return selectedNumber;
    }
}
