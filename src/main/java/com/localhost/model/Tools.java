package com.localhost.model;

import java.util.ArrayList;
import java.util.Comparator;

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

    public static synchronized <T> int nextId(ArrayList<T> list) {
        ArrayList<Id> newList = (ArrayList<Id>) list;
        return (newList.size() == 0) ? 1 : newList.stream().map(Id::getId).max(Comparator.naturalOrder()).get() + 1;
    }
}
