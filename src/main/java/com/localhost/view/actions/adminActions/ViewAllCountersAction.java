package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;
import com.localhost.view.actions.adminActions.AdminPageAction;

import java.util.Arrays;
import java.util.List;

public class ViewAllCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Существующие счётчики:");
//        Arrays.stream(session.getAdminSession().getAllSystemCounters()).forEach(counterType -> inputOutput.put(counterType));
        List<String> counters = session.getAdminSession().getAllSystemCounters();
        counters.stream().forEach(counterType -> inputOutput.put(counterType));
        session.addEvent(session.getLogin() + "просмотрел существующие счётчики.");
        inputOutput.put("Для выхода на предыдущий экран введите - p.");
        if (inputOutput.get().equals("p")) {
            return new AdminPageAction();
        }
        return this;
    }
}
