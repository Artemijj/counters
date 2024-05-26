package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.Tools;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.List;

public class UnlinkedUserCountersTypeAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя пользователя.");
        String login = inputOutput.get();
        inputOutput.put("Список подключенных счётчиков пользователя " + login + ":");
        List<String> counters;
        try {
            counters = session.getAdminSession().getUserCounters(login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int arrLength = counters.size();
        for (int i = 0; i < arrLength; i++) {
            inputOutput.put(i + " - " + counters.get(i));
        }

        inputOutput.put("Введите номер удаляемого счётчика.");
        String read = inputOutput.get();
        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new UnlinkedUserCountersTypeAction();
        }


        try {
            session.getAdminSession().unlinkCounter(login, counters.get(selectedNumber));
            session.addEvent(session.getLogin() + " удалил счётчик " + counters.get(selectedNumber) + " у пользователя " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
