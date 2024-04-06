package com.localhost.view;

import com.localhost.in.IUserSession;
import com.localhost.model.*;

import java.util.ArrayList;

public class ChoiceLinkedCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Выберите тип счётчика, для добавления в свой кабинет.");
        ArrayList<CounterType> counters = session.getModel().getCounters().getCounterList();
        int size = counters.size();
        for (int i = 0; i <= size - 1; i++) {
            System.out.println(i + " - " + counters.get(i).getCounterTypeName());
        }
        System.out.println(size + " - Выход на предыдущий экран.");

        int selectedNumber = Tools.parse(inputOutput.get());

        if (selectedNumber > size) {
            System.out.println("Такого счётчика не существует.");
        } else if (selectedNumber == size) {
                return new UserPageAction();
            }

        session.getModel().getUsers().getUser(session.getLogin()).addCounter(counters.get(selectedNumber));
        System.out.println("Счётчик добавлен.");
        session.addEvent("Добавил счётчик " + counters.get(selectedNumber).getCounterTypeName());

        return this;
    }
}
