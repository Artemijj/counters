package com.localhost.model;

import com.localhost.in.AdminException;

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

    public static <T> int nextIdList(ArrayList<T> list) {
        return (list.size() == 0) ? 1 : list.stream().map(T::getId).max(Comparator.naturalOrder()).get() + 1;
    }

    public static int nextIdJdbc() {
        return 0;
    }
}
