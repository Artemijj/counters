package com.localhost.view.actions.userActions;

import com.localhost.in.AddCounterException;
import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.*;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.ArrayList;

public class ChoiceUnlinkedCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите тип счётчика, для удаления из кабинета.");
////        ArrayList<CounterType> counters = session.getModelUsers().getUser(session.getLogin()).getUserCounters();
//        ArrayList<CounterType> counters = session.getModelUserCounters().getUserCountersListByUser(session.getLogin());
////        int size = counters.size();
////        for (int i = 0; i <= size - 1; i++) {
////            inputOutput.put(i + " - " + counters.get(i).getCounterTypeName());
////        }
        String[] types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.length;

        for (int i = 0; i <= arrLength - 1; i++) {
            inputOutput.put(i + " - " + types[i]);
        }
        inputOutput.put("p - Выход на предыдущий экран.");

        String read = inputOutput.get();


//        counters.stream()
//                .forEach(counterType -> inputOutput.put(counters.indexOf(counterType) + " - " + counterType.getCounterTypeName()));
//        inputOutput.put("p - Выход на предыдущий экран.");
//
//        String read = inputOutput.get();

        if (read.equals("p")) {
            return new UserPageAction();
        }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ChoiceUnlinkedCountersAction();
        }

//        session.getModelUsers().getUser(session.getLogin()).deleteCounter(counters.get(selectedNumber));
        try {
            session.deleteCounter(types[selectedNumber]);
        } catch (AddCounterException e) {
            throw new RuntimeException(e);
        }
        inputOutput.put("Счётчик удалён.");
        session.addEvent("Удалил из личного кабинета  счётчик - " + types[selectedNumber]);

        return this;
    }
}
