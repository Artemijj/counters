package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;

public class UserLoginAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Вход в личный кабинет.");
        inputOutput.put("Введите имя.");
        String login = inputOutput.get();
        inputOutput.put("Введите пароль.");
        String password = inputOutput.get();
        if (session.logIn(login, password)) {
            session.addEvent("Вошёл в личный кабинет.");
            return new UserPageAction();
        }
        return new FirstAction();
    }
}
