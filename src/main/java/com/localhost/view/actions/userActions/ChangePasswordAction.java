package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class ChangePasswordAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите новый пароль: \n" +
                "Для выхода на предыдущий экран введите - p.");
        String password = inputOutput.get();
        if (password.equals("p")) {
            return new UserPageAction();
        }
        inputOutput.put("Повторите пароль.");
        if(!inputOutput.get().equals(password)) {
            inputOutput.put("Пароли не совпадают.");
            return new UserPageAction();
        }
        try {
            session.getAdminSession().setPassword(session.getLogin(), password);
            inputOutput.put("Пароль изменён.");
            session.addEvent(session.getLogin() + " Изменил пароль.");
        } catch (AdminException e) {
            inputOutput.put("Пароль остался прежним.");
            session.addEvent(session.getLogin() + " Пытался изменить пароль.");
            return new UserPageAction();
        }
        return new UserPageAction();
    }
}
