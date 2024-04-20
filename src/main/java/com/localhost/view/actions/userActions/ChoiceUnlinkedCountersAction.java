package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.model.*;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.ArrayList;

public class ChoiceUnlinkedCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите тип счётчика, для удаления из кабинета.");
        ArrayList<CounterType> counters = session.getModelUsers().getUser(session.getLogin()).getUserCounters();
//        int size = counters.size();
//        for (int i = 0; i <= size - 1; i++) {
//            inputOutput.put(i + " - " + counters.get(i).getCounterTypeName());
//        }

        counters.stream()
                .forEach(counterType -> inputOutput.put(counters.indexOf(counterType) + " - " + counterType.getCounterTypeName()));
        inputOutput.put("p - Выход на предыдущий экран.");

        String read = inputOutput.get();

        if (read.equals("p")) {
            return new UserPageAction();
        }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= counters.size() || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ChoiceUnlinkedCountersAction();
        }

        session.getModelUsers().getUser(session.getLogin()).deleteCounter(counters.get(selectedNumber));
        inputOutput.put("Счётчик удалён.");
        session.addEvent("Удалил из личного кабинета  счётчик - " + counters.get(selectedNumber).getCounterTypeName());

        return this;
    }
}
