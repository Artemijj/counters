package com.localhost.view.actions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

import java.util.Arrays;

public class ViewUserActivitiesAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {

        String login;
        while (true) {
            inputOutput.put("Введите имя пользователя, для просмотра его действий.\n" +
                              "Для выхода на предыдущий экран введите - p.");
            login = inputOutput.get();
            if (!login.equals("p")) {
                try {
                    Arrays.stream(session.getAdminSession().getUserActivities(login))
                            .forEach(event -> inputOutput.put(event.getId() + " " + event.getLogin() + " " + event.getDate() + " " + event.getActivity()));
                } catch (AdminException e) {
                    throw new RuntimeException(e);
                }
                session.addEvent("Просмотр действий пользователя - " + login);
            } else {
                return new AdminPageAction();
            }
        }

    }
}
