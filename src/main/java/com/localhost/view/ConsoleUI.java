package com.localhost.view;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.actions.IAction;

public class ConsoleUI implements IConsoleUI{
    private IUserSession session;
    private IInputOutput inputOutput;

    public ConsoleUI() {
        session = new UserSession();
        inputOutput = new ConsoleInputOutput();
    }

    @Override
    public void start(IAction firstIAction) {
        IAction currentIAction = firstIAction;
        while (true) {
            currentIAction = currentIAction.execute(session, inputOutput);
        }
    }

    @Override
    public IUserSession getSession() {
        return session;
    }

}
