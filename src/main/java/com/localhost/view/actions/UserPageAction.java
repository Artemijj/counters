package com.localhost.view.actions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;

public class UserPageAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите желаемое действие.\n" +
                            "1 - Передача показаний.\n" +
                            "2 - Просмотр переданных показаний.\n" +
                            "3 - Просмотр переданных показаний за выбранныйый месяц.\n" +
                            "4 - Просмотр актуальных показаний счётчиков.\n" +
                            "5 - Подключение счётчика к личному кабинету.\n" +
                            "6 - Удаление счётчика из личного кабинета.\n" +
                            "q - Выход из сессии.");
        switch (inputOutput.get()) {
            case "1": return new ChoiceSendCounterAction();
            case "2": return new ChoiceViewedCountersAction();
            case "3": return new ViewCountersByMonthAction();
            case "4": return new ViewActualCountersAction();
            case "5": return new ChoiceLinkedCountersAction();
            case "6": return new ChoiceUnlinkedCountersAction();
            case "q": return new FirstAction();
            default: inputOutput.put("Введено некорректное значение.");
            return new UserPageAction();
        }
    }
}
