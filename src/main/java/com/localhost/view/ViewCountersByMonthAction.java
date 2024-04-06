package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.Tools;

import java.time.ZoneId;
import java.util.Arrays;

public class ViewCountersByMonthAction implements IAction{
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

        System.out.println("Введите год, в формате XXXX.");
        int year = Tools.parse(inputOutput.get());
        System.out.println("Введите номер месяца.");
        int month =  Tools.parse(inputOutput.get());

        CounterValue[] values;

        try {
            values = session.getCounterValues(types[selectedNumber]);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        Arrays.stream(values)
                .filter(counterValue ->  counterValue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
                .filter(counterValue ->  counterValue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month)
                .forEach(counterValue -> System.out.println(counterValue.getDate() + " | " + counterValue.getValue()));
        session.addEvent("Просмотрел показания счётчика " + types[selectedNumber].getCounterTypeName());

        return this;
    }
}
