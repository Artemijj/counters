package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.User;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ViewAllUsersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        try {
            List<User> users =  session.getAdminSession().getAllUsers();
//            Arrays.stream(session.getAdminSession().getAllUsers())
//                    .forEach(user -> inputOutput.put(user.getLogin() + " " + user.getAddress() + " " + user.getPhoneNumber() + " " + user.getIsAdmin()));
            users.stream()
                    .forEach(user -> inputOutput.put(user.getLogin() + " " + user.getFio() + " " + user.getAddress() + " " + user.getPhoneNumber() + " " + user.getIsAdmin()));
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        session.addEvent(session.getLogin() + " просмотрел существующих пользователей.");
        inputOutput.put("Для выхода на предыдущий экран введите - p.");
        if (inputOutput.get().equals("p")) {
            return new AdminPageAction();
        }
        return this;
    }
}
