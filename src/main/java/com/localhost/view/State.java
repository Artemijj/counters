package com.localhost.view;

import com.localhost.in.IUserSession;

public class State {
    private IUserSession session;
    private IInputOutput IInputOutput;

    void start(IAction firstIAction) {
        IAction currentIAction = firstIAction;
        while (true) {
            currentIAction = currentIAction.execute(session, IInputOutput);
        }
    }
}
