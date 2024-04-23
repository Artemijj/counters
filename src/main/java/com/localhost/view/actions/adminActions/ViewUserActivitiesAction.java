package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.Arrays;

public class ViewUserActivitiesAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {

        String login;
        inputOutput.put("Введите имя пользователя, для просмотра его действий.\n" +
                "Для выхода на предыдущий экран введите - p.");
        login = inputOutput.get();
        if (!login.equals("p")) {
            try {
                Arrays.stream(session.getAdminSession().getUserActivities(login))
                        .forEach(event -> inputOutput.put(event.getId() + " " + event.getLogin() + " " + event.getDate() + " " + event.getActivity()));
            } catch (AdminException e) {
                return new AdminPageAction();
            }
            session.addEvent(session.getLogin() + " просмотрел действия пользователя - " + login);
        } else {
            return new AdminPageAction();
        }
        return this;
    }
}
