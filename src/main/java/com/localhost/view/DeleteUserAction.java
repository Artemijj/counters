package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class DeleteUserAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя удаляемого пользователя.");
        String login = inputOutput.get();
        try {
            session.getAdminSession().removeUser(login);
            session.addEvent("Удаление пользователя - " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        return new AdminPageAction();
    }
}
