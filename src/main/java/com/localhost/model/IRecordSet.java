package com.localhost.model;

import java.util.ArrayList;

public interface IRecordSet {
    ArrayList<Record> getRecordSetList();
    boolean addRecord(Record record);
    void deleteRecord(Record record);
}
