package com.localhost.view;

import com.localhost.in.IUserSession;

import java.util.Arrays;

public class ViewAllCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        Arrays.stream(session.getAdminSession().getAllSystemCounters()).forEach(counterType -> System.out.println(counterType.getCounterTypeName()));
        session.addEvent("Просмотр существующих счётчиков.");
        System.out.println("Для выхода на предыдущий экран введите - 0.");
        while (true) {
            if (inputOutput.get().equals("0")) {
                return new AdminPageAction();
            }
        }
    }
}
