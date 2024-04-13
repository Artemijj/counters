package com.localhost.view.actions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

public class FirstAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Зарегистрируйтесь или войдите в систему передачи показаний счётчиков.\n" +
                            "Введите:\n" +
                            "1 - для регистрации.\n" +
                            "2 - для входа.\n" +
                            "3 - для входа с правами админстратора.");
        switch (inputOutput.get()) {
            case "1": return new RegistrationAction();
            case "2": return new UserLoginAction();
            case "3": return new AdminLoginAction();
            default: inputOutput.put("Введено некорректное значение.");
            return this;
        }
    }
}
