package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.Tools;
import com.localhost.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

public class RecordSetListTest {
    private IRecordSet recordSet;

//    @Mock
//    User user;
    private String counterType = "type";// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private CounterValue counterValue = new CounterValue(new Date(), 1);// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private Record record = new Record(1, "newUser", counterType, counterValue);

    @BeforeEach
    public void setUp() {
        recordSet = new RecordSetList();
    }

    @Test
    public void getRecordSetListTest() {
        int expectedNumber = 0;
        int actualNumber = recordSet.getRecordSetList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addRecordTrueTest() {
        boolean actual = recordSet.addRecord(record);
        Assertions.assertTrue(actual);
    }

    @Test // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void addRecordFalseTest() {
        recordSet.addRecord(record);
        boolean actual = recordSet.addRecord(record);
        Assertions.assertFalse(actual);
    }
//
//    @Test
//    public void nextIdTest() {
//        int expectedId = 1;
//        int actualId = Tools.nextId(recordSet.getRecordSetList());
//        Assertions.assertEquals(expectedId, actualId);
//    }
}
