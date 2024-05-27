package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.List;

public class ViewOwnCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        try {
            List<String> counters = session.getAdminSession().getUserCounters(session.getLogin());
            counters.forEach(counterType -> inputOutput.put(counterType));
        } catch (AdminException e) {
            session.addEvent(session.getLogin() + " Пытался просмотрть список своих счётчиков.");
            return new UserPageAction();
        }
        session.addEvent(session.getLogin() + " Просмотрел список своих счётчиков.");
        return new UserPageAction();
    }
}
