package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class CreateNewCounterTypeAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String counterType;
        while (true) {
            System.out.println("Введите название нового счётчика.\n" +
                                "Для выхода на предыдущий экран введите - 0.");
            counterType = inputOutput.get();
            if (!counterType.equals("0")) {
                try {
                    session.getAdminSession().createCounter("");
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
