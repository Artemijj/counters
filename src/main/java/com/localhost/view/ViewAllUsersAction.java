package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;

import java.util.Arrays;

public class ViewAllUsersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        try {
            Arrays.stream(session.getAdminSession().getAllUsers())
                    .forEach(user -> System.out.println(user.getLogin() + " " + user.getAddress() + " " + user.getPhoneNumber() + " " + user.getIsAdmin()));
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        session.addEvent("Просмотр существующих пользователей.");
        System.out.println("Для выхода на предыдущий экран введите - 0.");
        while (true) {
            if (inputOutput.get().equals("0")) {
                return new AdminPageAction();
            }
        }
    }
}
