package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class DeleteUserAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя удаляемого пользователя.");
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
