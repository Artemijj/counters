package com.localhost.view;

import com.localhost.in.IUserSession;
import com.localhost.view.actions.IAction;

public interface IConsoleUI {
    void start(IAction action);
    IUserSession getSession();
}
