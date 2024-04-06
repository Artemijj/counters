package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class SetUserPasswordAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя пользователя.");
        String login = inputOutput.get();
        System.out.println("Введите новый пароль пользователя.");
        String password = inputOutput.get();
        try {
            session.getAdminSession().setPassword(login, password);
            session.addEvent("Изменение пароля пользователя - " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
