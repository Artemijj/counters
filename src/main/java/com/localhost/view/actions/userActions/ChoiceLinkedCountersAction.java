package com.localhost.view.actions.userActions;

import com.localhost.in.AddCounterException;
import com.localhost.in.IUserSession;
import com.localhost.model.*;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.List;

public class ChoiceLinkedCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите тип счётчика, для добавления в свой кабинет.");
        List<String> counters = session.getAdminSession().getAllSystemCounters();
        int length = counters.size();
        for (int i = 0; i < length; i++) {
            inputOutput.put(i + " - " + counters.get(i));
        }

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

        try {
            session.addCounter(counters.get(selectedNumber));
        } catch (AddCounterException e) {
            throw new RuntimeException(e);
        }
        inputOutput.put("Счётчик добавлен.");
        session.addEvent("Добавил счётчик " + counters.get(selectedNumber));

        return this;
    }
}
