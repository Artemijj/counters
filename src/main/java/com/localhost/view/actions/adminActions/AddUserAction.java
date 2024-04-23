package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class AddUserAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя нового пользователя.");
        String login;
        login = inputOutput.get();
        if (session.isUserExist(login)) {
            inputOutput.put("Пользователь с таким именем существует.\n" +
                    "Введите другое имя пользователя.");
        } else {
            inputOutput.put("Введите пароль нового пользователя.");
            String password = inputOutput.get();
            try {
                session.getAdminSession().addUser(login, password);
                session.addEvent("Создание пользователя - " + login);
            } catch (AdminException e) {
                return new AdminPageAction();
            }
        }
        return new AdminPageAction();
    }
}
