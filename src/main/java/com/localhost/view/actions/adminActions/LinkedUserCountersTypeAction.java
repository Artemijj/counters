package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.Tools;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.List;

public class LinkedUserCountersTypeAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Список существующих счётчиков:");
        List<String> counters = session.getAdminSession().getAllSystemCounters();
        int arrLength = counters.size();
        for (int i = 0; i < arrLength; i++) {
            inputOutput.put(i + " - " + counters.get(i));
        }

        inputOutput.put("Введите имя пользователя.");
        String login = inputOutput.get();
        inputOutput.put("Введите номер добавляемого счётчика.");
        String read = inputOutput.get();
        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new LinkedUserCountersTypeAction();
        }

        try {
            session.getAdminSession().linkCounter(login, counters.get(selectedNumber));
            session.addEvent(session.getLogin() + " добавил счётчик " + counters.get(selectedNumber) + " пользователю " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
