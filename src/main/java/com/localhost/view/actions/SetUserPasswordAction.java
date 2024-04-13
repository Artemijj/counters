package com.localhost.view.actions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

public class SetUserPasswordAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя пользователя.");
        String login = inputOutput.get();
        inputOutput.put("Введите новый пароль пользователя.");
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
