package com.localhost.view.actions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

public class AddAdminAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя нового администратора.");
        String login;
        while (true) {
            login = inputOutput.get();
            if (session.isUserExist(login)) {
                inputOutput.put("Пользователь с таким именем существует.\n +" +
                        "Введите другое имя администратора.");
            } else {
                break;
            }
        }
        inputOutput.put("Введите пароль нового администратора.");
        String password = inputOutput.get();
        try {
            session.getAdminSession().addAdmin(login, password);
            session.addEvent("Создание администратора - " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
