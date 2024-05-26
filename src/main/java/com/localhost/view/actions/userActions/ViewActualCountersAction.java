package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ViewActualCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        List<String> counterTypes;
        try {
            counterTypes = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        counterTypes
                .forEach(type -> inputOutput.put(type + "\n" + (session.getLastValue(type).getDate().equals(new Date(0L)) ? "0" : session.getLastValue(type).getDate()) + " | " + session.getLastValue(type).getValue()));
        session.addEvent("Просмотрел актуальные показания всех счётчиков.");

        inputOutput.put("Выберите следующее действие:\n" +
                "p - Для выхода на предыдущий экран.\n" +
                "q - Для выхода из своей сессии.");

        switch (inputOutput.get()) {
            case "p": return new UserPageAction();
            case "q": return new FirstAction();
        }

        return null;
    }
}
