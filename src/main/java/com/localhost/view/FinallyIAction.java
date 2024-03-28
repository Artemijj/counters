package com.localhost.view;

import com.localhost.in.IUserSession;

public class FinallyIAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput IInputOutput) {
        IInputOutput.put("Program is over.");
        System.exit(0);
        return null;
    }
}
