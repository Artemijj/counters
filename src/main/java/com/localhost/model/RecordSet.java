package com.localhost.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class RecordSet implements IRecordSet{

    private static ArrayList<Record> records;

    public RecordSet() {
        records = new ArrayList<>();
    }

    @Override
    public ArrayList<Record> getRecordSetList() {
        return records;
    }

    @Override
    public boolean addRecord(Record record) {
        boolean answer = false;
        if (!records.contains(record)) {
            records.add(record);
            answer = true;
        }
        return answer;
    }

    @Override
    public void deleteRecord(Record record) {
        records.remove(record);
    }

    @Override
    public int nextId() {
        return records.stream().map(Record::getId).max(Comparator.naturalOrder()).get() + 1;
    }

//    public ArrayList<Record> getReadingsByLogin(String login) {
//        return (ArrayList<Record>) records.stream().filter(record -> record.getUser().equals(Users.getUser(login))).collect(Collectors.toList());
//    }
//
//    public ArrayList<Record> getReadingsByCounterType(CounterType counterType) {
//        return (ArrayList<Record>) records.stream().filter(record -> record.getCounterType().equals(counterType));
//    }
}
