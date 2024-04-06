package com.localhost.view;

import com.localhost.in.IUserSession;

public class UserPageAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        System.out.println("Выберите желаемое действие.\n" +
                            "1 - Передача показаний.\n" +
                            "2 - Просмотр переданных показаний.\n" +
                            "3 - Просмотр переданных показаний за выбранныйый месяц.\n" +
                            "4 - Просмотр актуальных показаний счётчиков.\n" +
                            "5 - Подключение счётчика к личному кабинету.\n" +
                            "5 - Удаление счётчика из личного кабинета.\n" +
                            "7 - Выход из сессии.");
        switch (inputOutput.get()) {
            case "1": return new ChoiceSendCounterAction();
            case "2": return new ChoiceViewedCountersAction();
            case "3": return new ViewCountersByMonthAction();
            case "4": return new ViewActualCountersAction();
            case "5": return new ChoiceLinkedCountersAction();
            case "6": return new ChoiceUnlinkedCountersAction();
            case "7": return new FirstAction();
        }
        return null;
    }
}
