package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class UnlinkedUserCountersTypeAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя пользователя.");
        String login = inputOutput.get();
        System.out.println("Введите тип счётчика.");
        String counterType = inputOutput.get();
        try {
            session.getAdminSession().unlinkCounter(login, session.getModel().getCounters().getCounter(counterType));
            session.addEvent("Удаление счётчика " + counterType + " у пользователя " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
