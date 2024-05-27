package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

public class ViewOwnCredentialsAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Логин: " + session.getModelUsers().getUser(session.getLogin()).getLogin());
        inputOutput.put("Фамилия имя отчество: " + session.getModelUsers().getUser(session.getLogin()).getFio());
        inputOutput.put("Адрес: " + session.getModelUsers().getUser(session.getLogin()).getAddress());
        inputOutput.put("Номер телефона: " + session.getModelUsers().getUser(session.getLogin()).getPhoneNumber());
        session.addEvent(session.getLogin() + " Просмотрел свои учётные данные.");
        return new UserPageAction();
    }
}
