package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;

public class AdminLoginAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя.");
        String login = inputOutput.get();
        inputOutput.put("Введите пароль.");
        String password = inputOutput.get();
        if (session.logIn(login, password)) {
            return new AdminPageAction();
        }
        return new FirstAction();
    }
}
