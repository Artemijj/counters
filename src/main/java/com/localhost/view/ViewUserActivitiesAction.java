package com.localhost.view;

import com.localhost.in.IUserSession;

import java.util.Arrays;

public class ViewUserActivitiesAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {

        String login;
        while (true) {
            System.out.println("Введите имя пользователя, для просмотра его действий.\n" +
                              "Для выхода на предыдущий экран введите - 0.");
            login = inputOutput.get();
            if (!login.equals("0")) {
                Arrays.stream(session.getAdminSession().getUserActivities(login))
                        .forEach(event -> System.out.println(event.getId() + " " + event.getUser() + " " + event.getDate() + " " + event.getActivity()));
                session.addEvent("Просмотр действий пользователя - " + login);
            } else {
                return new AdminPageAction();
            }
        }

    }
}
