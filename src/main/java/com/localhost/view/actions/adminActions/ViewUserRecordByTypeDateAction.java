package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.model.Tools;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.NoSuchElementException;

public class ViewUserRecordByTypeDateAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        inputOutput.put("Введите имя пользователя.\n" +
                "Для выхода на предыдущий экран введите - p.");
        String login = inputOutput.get();
        if (login.equals("p")) {
            return new AdminPageAction();
        }
        inputOutput.put("Введите тип счётчика.\n" +
                "Для выхода на предыдущий экран введите - p.");
        String type = inputOutput.get();
        if (type.equals("p")) {
            return new AdminPageAction();
        }
        inputOutput.put("Введите год, в формате XXXX.\n" +
                "Для выхода на предыдущий экран введите - p.");
        String stringYear = inputOutput.get();
        if (stringYear.equals("p")) {
            return new AdminPageAction();
        }
        int year = Tools.parse(stringYear);
        inputOutput.put("Введите номер месяца.\n" +
                "Для выхода на предыдущий экран введите - p.");
        String stringMonth = inputOutput.get();
        if (stringMonth.equals("p")) {
            return new AdminPageAction();
        }
        int month = Tools.parse(stringYear);
        int value = 0;
        try {
            value = session.getModelRecordSet().getRecordSetListByUserTypeDate(login, type, month, year).stream().findFirst().map(record -> record.getCounterValue().getValue()).get();
//                    .get(0).getCounterValue().getValue();
        } catch (NoSuchElementException e) {
            inputOutput.put("Показания за этот месяц не передавались.");
            return new AdminPageAction();
        }
        inputOutput.put("Переданное показание счётчика " + type + " - " + value);
        session.addEvent(session.getLogin() + " Просмотрел показания счётчика " + type + ", принадлежащего " + login + ", за " + month + "." + year);
        return new AdminPageAction();
    }
}
