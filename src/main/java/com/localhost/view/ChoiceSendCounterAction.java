package com.localhost.view;

import com.localhost.in.*;
import com.localhost.model.*;
import com.localhost.model.Record;

import java.util.ArrayList;
import java.util.Date;

public class ChoiceSendCounterAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        CounterType[] types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.length;

        System.out.println("Выберите тип счётчика, для передачи показаний.");

        for (int i = 0; i <= arrLength - 1; i++) {
            System.out.println(i + " - " + types[i].getCounterTypeName());
        }
        System.out.println(arrLength + " - Выход на предыдущий экран.");

        int selectedNumber = Tools.parse(inputOutput.get());

        if (selectedNumber > arrLength) {
            System.out.println("Такого счётчика не существует.");
        } else if (selectedNumber == arrLength) {
            return new UserPageAction();
        }

        System.out.println("Введите показание счётчика (целое число).");
        int value = Integer.parseInt(inputOutput.get());
        int id = session.getModel().getRecordSet().nextId();
        User user = session.getModel().getUsers().getUser(session.getLogin());
        CounterType counterType = types[selectedNumber];
        CounterValue counterValue = new CounterValue(new Date(), value);
        session.getModel().getRecordSet().addRecord(new Record(id, user, counterType, counterValue));
        session.addEvent("Передал показания счётчика " + types[selectedNumber].getCounterTypeName());

        return this;
    }
}
