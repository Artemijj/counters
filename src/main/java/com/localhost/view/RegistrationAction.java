package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IAdminSession;
import com.localhost.in.IUserSession;

public class RegistrationAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Введите имя.");
        String name = inputOutput.get();
        System.out.println("Введите пароль.");
        String password = inputOutput.get();
//        System.out.println("Повторите пароль.");
//        if(!inputOutput.get().equals(password)) {
//            System.out.println("Пароли не совпадают");
//        }
        System.out.println("Введите номер телефона.");
        String phone = inputOutput.get();
        System.out.println("Введите адрес.");
        String address = inputOutput.get();
        IAdminSession adminSession = session.getAdminSession();
        try {
            adminSession.addUser(name, password);
            adminSession.setPhone(name, phone);
            adminSession.setAddress(name, address);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        return new UserLoginAction();
    }
}
