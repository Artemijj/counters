package com.localhost.model.records;

import com.localhost.model.Record;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static java.util.stream.Collectors.toCollection;

public class RecordSetList implements IRecordSet {

    private static ArrayList<Record> records;

    public RecordSetList() {
        records = new ArrayList<>();
    }

    @Override
    public ArrayList<Record> getRecordSetList() {
        return records;
    }

    @Override
    public boolean addRecord(Record record) {
        boolean answer = false;
        int actualMonth = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        int actualYear = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        long recordCount = records.stream()
                .filter(rec -> rec.getCounterType().equals(record.getCounterType()))
                .filter(rec -> rec.getCounterValue().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == actualYear)
                .filter(rec -> rec.getCounterValue().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == actualMonth)
                .count();
        if ((recordCount == 0) && (!records.contains(record))) {
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
    public ArrayList<Record> getRecordSetListByUserAndType(String login, String type) {
        ArrayList<Record> newRecords = records.stream()
                .filter(record -> record.getLogin().equals(login))
                .filter(record -> record.getCounterType().equals(type))
                .collect(toCollection(ArrayList::new));
        return newRecords;
    }

    @Override
    public ArrayList<Record> getRecordSetListByUserTypeDate(String login, String type, int month, int year) {
        ArrayList<Record> newRecords = records.stream()
                .filter(record -> record.getLogin().equals(login))
                .filter(record -> record.getCounterType().equals(type))
                .filter(record -> record.getCounterValue().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month)
                .filter(record -> record.getCounterValue().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
                .collect(toCollection(ArrayList::new));
        return newRecords;
    }

//    @Override
//    public int nextId() {
//        return (records.size() == 0) ? 1 : records.stream().map(Record::getId).max(Comparator.naturalOrder()).get() + 1;
//    }

}
