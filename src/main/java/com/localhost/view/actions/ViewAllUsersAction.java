package com.localhost.view.actions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

import java.util.Arrays;

public class ViewAllUsersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        try {
            Arrays.stream(session.getAdminSession().getAllUsers())
                    .forEach(user -> inputOutput.put(user.getLogin() + " " + user.getAddress() + " " + user.getPhoneNumber() + " " + user.getIsAdmin()));
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        session.addEvent("Просмотр существующих пользователей.");
        inputOutput.put("Для выхода на предыдущий экран введите - p.");
        while (true) {
            if (inputOutput.get().equals("p")) {
                return new AdminPageAction();
            }
        }
    }
}
