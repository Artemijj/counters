package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class ChangePhoneNumberAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите новый номер телефона: \n" +
                "Для выхода на предыдущий экран введите - p.");
        String phone = inputOutput.get();
        if (phone.equals("p")) {
            return new UserPageAction();
        }
        try {
            session.getAdminSession().setPhone(session.getLogin(), phone);
            inputOutput.put("Установлен номер телефона: " + phone);
            session.addEvent(session.getLogin() + " Изменил номер телефона.");
        } catch (AdminException e) {
            inputOutput.put("Номер телефона остался прежним.");
            session.addEvent(session.getLogin() + " Пытался изменить номер телефона.");
            return new UserPageAction();
        }
        return new UserPageAction();
    }
}
