package com.localhost.view;

import com.localhost.in.*;
import com.localhost.model.*;
import com.localhost.model.Record;

import java.util.ArrayList;

public class ChoiceViewedCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        CounterType[] types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.length;

        System.out.println("Выберите тип счётчика, для просмотра показаний.");
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

        System.out.println("Показания счётчика " + types[selectedNumber].getCounterTypeName() + ":");
        session.getModel().getRecordSet().getRecordSetList().stream()
                        .filter(record -> record.getUser().equals(session.getModel().getUsers().getUser(session.getLogin())))
                        .filter(record -> record.getCounterType().equals(types[selectedNumber]))
                        .map(Record::getCounterValue)
                        .forEach(counterValue -> System.out.println(counterValue.getDate() + " | " + counterValue.getValue()));
        session.addEvent("Просмотрел показания счётчика " + types[selectedNumber].getCounterTypeName());

        return this;
    }
}
