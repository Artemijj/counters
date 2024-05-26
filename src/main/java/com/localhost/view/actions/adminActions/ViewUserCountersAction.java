package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;
import com.localhost.view.actions.adminActions.AdminPageAction;

import java.util.Arrays;
import java.util.List;

public class ViewUserCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String login;
        inputOutput.put("Введите имя пользователя, для просмотра списка его счётчиков.\n" +
                "Для выхода на предыдущий экран введите - p.");
        login = inputOutput.get();
        if (!login.equals("p")) {
            try {
//                Arrays.stream(session.getAdminSession().getUserCounters(login))
//                        .forEach(counterType -> inputOutput.put(counterType));
                List<String> counters = session.getAdminSession().getUserCounters(login);
                counters.forEach(counterType -> inputOutput.put(counterType));
            } catch (AdminException e) {
                return new AdminPageAction();
            }
            session.addEvent("Просмотр списка счётчиков пользователя - " + login);
        } else {
            return new AdminPageAction();
        }
        return this;
    }
}
