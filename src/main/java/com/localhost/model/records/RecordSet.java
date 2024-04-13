package com.localhost.model.records;

import com.localhost.model.Record;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

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
    public int nextId() {
        return (records.size() == 0) ? 1 : records.stream().map(Record::getId).max(Comparator.naturalOrder()).get() + 1;
    }

//    public ArrayList<Record> getReadingsByLogin(String login) {
//        return (ArrayList<Record>) records.stream().filter(record -> record.getUser().equals(Users.getUser(login))).collect(Collectors.toList());
//    }
//
//    public ArrayList<Record> getReadingsByCounterType(CounterType counterType) {
//        return (ArrayList<Record>) records.stream().filter(record -> record.getCounterType().equals(counterType));
//    }
}
