package com.localhost.view.actions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

public interface IAction {
        IAction execute(IUserSession session, IInputOutput inputOutput);
}
