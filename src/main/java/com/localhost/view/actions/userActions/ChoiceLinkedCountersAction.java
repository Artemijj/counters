package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.model.*;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class ChoiceLinkedCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите тип счётчика, для добавления в свой кабинет.");
//        ArrayList<CounterType> counters = session.getModelCounters().getCounterList();
////        int size = counters.size();
////        for (int i = 0; i <= size - 1; i++) {
////            inputOutput.put(i + " - " + counters.get(i).getCounterTypeName());
////        }
        CounterType[] counters = session.getAdminSession().getAllSystemCounters();
        int length = counters.length;
        for (int i = 0; i < length; i++) {
            inputOutput.put(i + " - " + counters[i].getCounterTypeName());
        }

//        counters.stream()
//                .forEach(counterType -> inputOutput.put(counters.indexOf(counterType) + " - " + counterType.getCounterTypeName()));
        inputOutput.put("p - Выход на предыдущий экран.");

        inputOutput.put("Введите номер добавляемого счётчика.");

        String read = inputOutput.get();

        if (read.equals("p")) {
                return new UserPageAction();
            }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= length || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ChoiceLinkedCountersAction();
        }

        session.getModelUsers().getUser(session.getLogin()).addCounter(counters[selectedNumber]);
        inputOutput.put("Счётчик добавлен.");
        session.addEvent("Добавил счётчик " + counters[selectedNumber].getCounterTypeName());

        return this;
    }
}
