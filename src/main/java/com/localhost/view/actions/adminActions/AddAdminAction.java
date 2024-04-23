package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class AddAdminAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя нового администратора.");
        String login;
        login = inputOutput.get();
        if (session.isUserExist(login)) {
            inputOutput.put("Пользователь с таким именем существует.\n" +
                    "Введите другое имя администратора.");
        } else {
            inputOutput.put("Введите пароль нового администратора.");
            String password = inputOutput.get();
            try {
                session.getAdminSession().addAdmin(login, password);
                session.addEvent("Администратор " + session.getLogin() + " создал администратора - " + login);
            } catch (AdminException e) {
                return new AdminPageAction();
            }
        }
        return new AdminPageAction();
    }
}
