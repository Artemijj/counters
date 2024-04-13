package com.localhost.view.actions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

import java.util.Arrays;

public class ViewUserCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String login;
        while (true) {
            inputOutput.put("Введите имя пользователя, для просмотра списка его счётчиков.\n" +
                    "Для выхода на предыдущий экран введите - p.");
            login = inputOutput.get();
            if (!login.equals("p")) {
                try {
                    Arrays.stream(session.getAdminSession().getUserCounters(login))
                            .forEach(counterType -> inputOutput.put(counterType.getCounterTypeName()));
                } catch (AdminException e) {
                    throw new RuntimeException(e);
                }
                session.addEvent("Просмотр списка счётчиков пользователя - " + login);
            } else {
                return new AdminPageAction();
            }
        }
    }
}
