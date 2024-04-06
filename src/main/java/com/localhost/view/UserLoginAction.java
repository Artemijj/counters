package com.localhost.view;

import com.localhost.in.IUserSession;

public class UserLoginAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя.");
        String login = inputOutput.get();
        System.out.println("Введите пароль.");
        String password = inputOutput.get();
        if (session.logIn(login, password)) {
            return new UserPageAction();
        }
        return new FirstAction();
    }
}
