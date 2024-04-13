package com.localhost.view;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.actions.IAction;

public class State {
    private IUserSession session = new UserSession();
    private IInputOutput inputOutput = new ConsoleInputOutput();

    public void start(IAction firstIAction) {
        IAction currentIAction = firstIAction;
        while (true) {
            currentIAction = currentIAction.execute(session, inputOutput);
        }
    }
}
