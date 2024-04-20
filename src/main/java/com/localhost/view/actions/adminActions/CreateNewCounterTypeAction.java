package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class CreateNewCounterTypeAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String counterType;
        while (true) {
            inputOutput.put("Введите название нового счётчика.\n" +
                                "Для выхода на предыдущий экран введите - p.");
            counterType = inputOutput.get();
            if (!counterType.equals("p")) {
                try {
                    session.getAdminSession().createCounter(counterType);
                } catch (AdminException e) {
                    throw new RuntimeException(e);
                }
                session.addEvent("Создание счётчика - " + counterType);
            } else {
                return new AdminPageAction();
            }

        }
    }
}
