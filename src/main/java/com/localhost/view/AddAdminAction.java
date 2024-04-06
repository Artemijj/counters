package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class AddAdminAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя нового администратора.");
        String login;
        while (true) {
            login = inputOutput.get();
            if (session.getAdminSession().isUserExist(login)) {
                System.out.println("Пользователь с таким именем существует.\n +" +
                        "Введите другое имя администратора.");
            } else {
                break;
            }
        }
        System.out.println("Введите пароль нового администратора.");
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
