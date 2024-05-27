package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.*;

public class AdminPageAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Выберите желаемое действие.\n" +
                "1 - Добавить пользователя.\n" +
                "2 - Удалить пользователя.\n" +
                "3 - Добавить администратора.\n" +
                "4 - Посмотреть список пользователей.\n" +
                "5 - Посмотреть все типы счётчиков.\n" +
                "6 - Добавить новый тип счётчика.\n" +
                "7 - Посмотреть действия пользователя.\n" +
                "8 - Посмотреть список счётчиков пользователя.\n" +
                "9 - Добавить пользователю тип счётчика.\n" +
                "10 - Удалить у пользователя тип счётчика.\n" +
                "11 - Установить пароль пользователя.\n" +
                "12 - Посмотреть переданные показания пользователя по типу счётчика и дате.\n" +
                "q - Выход из сессии.");
        switch (inputOutput.get()) {
            case "1":
                return new AddUserAction();
            case "2":
                return new DeleteUserAction();
            case "3":
                return new AddAdminAction();
            case "4":
                return new ViewAllUsersAction();
            case "5":
                return new ViewAllCountersAction();
            case "6":
                return new CreateNewCounterTypeAction();
            case "7":
                return new ViewUserActivitiesAction();
            case "8":
                return new ViewUserCountersAction();
            case "9":
                return new LinkedUserCountersTypeAction();
            case "10":
                return new UnlinkedUserCountersTypeAction();
            case "11":
                return new SetUserPasswordAction();
            case "12":
                return new ViewUserRecordByTypeDateAction();
            case "q":
                return new FirstAction();
            default:
                inputOutput.put("Введено некорректное значение.");
                return this;
        }
    }
}
