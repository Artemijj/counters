package com.localhost.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Readings {

    private static ArrayList<Reading> readings;

    public Readings() {
        readings = new ArrayList<>();
    }

    public static ArrayList<Reading> getReadings() {
        return readings;
    }

    public static boolean addReading(Reading reading) {
        boolean answer = false;
        if (!readings.contains(reading)) {
            readings.add(reading);
            answer = true;
        }
        return answer;
    }

    public static void deleteReading(Reading reading) {
        readings.remove(reading);
    }

    public static ArrayList<Reading> getReadingsByLogin(String login) {
        return (ArrayList<Reading>) readings.stream().filter(reading -> reading.getUser().equals(Users.getUser(login))).collect(Collectors.toList());
    }

    public static ArrayList<Reading> getReadingsByCounterType(CounterType counterType) {
        return (ArrayList<Reading>) readings.stream().filter(reading -> reading.getCounterType().equals(counterType)).collect(Collectors.toList());
    }
}
