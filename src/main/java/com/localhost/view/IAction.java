package com.localhost.view;

import com.localhost.in.IUserSession;

public interface IAction {
    IAction execute(IUserSession session, IInputOutput IInputOutput);
}
