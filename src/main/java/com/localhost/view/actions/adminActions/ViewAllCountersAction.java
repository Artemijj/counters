package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;
import com.localhost.view.actions.adminActions.AdminPageAction;

import java.util.Arrays;

public class ViewAllCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Существующие счётчики:");
        Arrays.stream(session.getAdminSession().getAllSystemCounters()).forEach(counterType -> inputOutput.put(counterType.getCounterTypeName()));
        session.addEvent(session.getLogin() + "просмотрел существующие счётчики.");
        inputOutput.put("Для выхода на предыдущий экран введите - p.");
        if (inputOutput.get().equals("p")) {
            return new AdminPageAction();
        }
        return this;
    }
}
