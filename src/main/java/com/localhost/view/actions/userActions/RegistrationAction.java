package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IAdminSession;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;

public class RegistrationAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя.");
        String name = inputOutput.get();
        inputOutput.put("Введите пароль.");
        String password = inputOutput.get();
        inputOutput.put("Повторите пароль.");
        if(!inputOutput.get().equals(password)) {
            inputOutput.put("Пароли не совпадают.");
            return new FirstAction();
        }
        inputOutput.put("Введите фамилию имя отчество:");
        String fio = inputOutput.get();
        inputOutput.put("Введите номер телефона.");
        String phone = inputOutput.get();
        inputOutput.put("Введите адрес.");
        String address = inputOutput.get();
        IAdminSession adminSession = session.getAdminSession();
        try {
            adminSession.addUser(name, password);
            adminSession.setFio(name, fio);
            adminSession.setPhone(name, phone);
            adminSession.setAddress(name, address);
            session.addEvent("Зарегистрировался пользователь с именем: " + name);
        } catch (AdminException e) {
            return new FirstAction();
        }
        return new UserLoginAction();
    }
}
