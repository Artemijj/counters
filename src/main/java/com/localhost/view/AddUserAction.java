package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

public class AddUserAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя нового пользователя.");
        String login;
        while (true) {
            login = inputOutput.get();
            if (session.getAdminSession().isUserExist(login)) {
                System.out.println("Пользователь с таким именем существует.\n +" +
                                   "Введите другое имя пользователя.");
            } else {
                break;
            }
        }
        System.out.println("Введите пароль нового пользователя.");
        String password = inputOutput.get();
        try {
            session.getAdminSession().addUser(login, password);
            session.addEvent("Создание пользователя - " + login);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        return new AdminPageAction();
    }
}
