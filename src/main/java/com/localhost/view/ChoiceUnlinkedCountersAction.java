package com.localhost.view;

import com.localhost.in.IUserSession;
import com.localhost.model.*;

import java.util.ArrayList;

public class ChoiceUnlinkedCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Выберите тип счётчика, для удаления из кабинета.");
        ArrayList<CounterType> counters = session.getModel().getUsers().getUser(session.getLogin()).getUserCounters();
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



        session.getModel().getUsers().getUser(session.getLogin()).deleteCounter(counters.get(selectedNumber));
        System.out.println("Счётчик удалён.");
        session.addEvent("Удалил из личного кабинета  счётчик - " + counters.get(selectedNumber).getCounterTypeName());

        return this;
    }
}
