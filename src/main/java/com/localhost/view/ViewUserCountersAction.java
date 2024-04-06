package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

import java.util.Arrays;

public class ViewUserCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String login;
        while (true) {
            System.out.println("Введите имя пользователя, для просмотра списка его счётчиков.\n" +
                    "Для выхода на предыдущий экран введите - 0.");
            login = inputOutput.get();
            if (!login.equals("0")) {
                try {
                    Arrays.stream(session.getAdminSession().getUserCounters(login))
                            .forEach(counterType -> System.out.println(counterType.getCounterTypeName()));
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
