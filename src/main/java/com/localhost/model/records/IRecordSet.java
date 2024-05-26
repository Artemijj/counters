package com.localhost.model.records;

import com.localhost.model.Record;

import java.util.ArrayList;

public interface IRecordSet {
    ArrayList<Record> getRecordSetList();
    boolean addRecord(Record record);
    void deleteRecord(Record record);
    ArrayList<Record> getRecordSetListByUserAndType(String login, String type);
    ArrayList<Record> getRecordSetListByUserTypeDate(String login, String type, int month, int year);
//    int nextId();
}
