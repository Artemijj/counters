package com.localhost.model.records;

import com.localhost.model.Record;

import java.util.ArrayList;

public interface IRecordSet {
    ArrayList<Record> getRecordSetList();
    boolean addRecord(Record record);
    void deleteRecord(Record record);
    int nextId();
}
