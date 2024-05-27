package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;

public class UserPageAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите желаемое действие.\n" +
                "1 - Передача показаний.\n" +
                "2 - Просмотр переданных показаний.\n" +
                "3 - Просмотр переданных показаний за выбранный месяц.\n" +
                "4 - Просмотр актуальных показаний счётчиков.\n" +
                "5 - Подключение счётчика к личному кабинету.\n" +
                "6 - Удаление счётчика из личного кабинета.\n" +
                "7 - Просмотр своих учётных данных.\n" +
                "8 - Изменение пароля.\n" +
                "9 - Изменение номера телефона.\n" +
                "10 - Просмотр своих подключенных счётчиков.\n" +
                "q - Выход из сессии.");
        switch (inputOutput.get()) {
            case "1":
                return new ChoiceSendCounterAction();
            case "2":
                return new ChoiceViewedCountersAction();
            case "3":
                return new ViewCountersByMonthAction();
            case "4":
                return new ViewActualCountersAction();
            case "5":
                return new ChoiceLinkedCountersAction();
            case "6":
                return new ChoiceUnlinkedCountersAction();
            case "7":
                return new ViewOwnCredentialsAction();
            case "8":
                return new ChangePasswordAction();
            case "9":
                return new ChangePhoneNumberAction();
            case "10":
                return new ViewOwnCountersAction();
            case "q":
                return new FirstAction();
            default:
                inputOutput.put("Введено некорректное значение.");
                return new UserPageAction();
        }
    }
}
