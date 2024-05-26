package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.*;
import com.localhost.model.Record;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.List;

public class ChoiceViewedCountersAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        List<String> types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.size();

        inputOutput.put("Выберите тип счётчика, для просмотра показаний.");
        for (int i = 0; i <= arrLength - 1; i++) {
            inputOutput.put(i + " - " + types.get(i));
        }
        inputOutput.put("p - Выход на предыдущий экран.");

        String read = inputOutput.get();

        if (read.equals("p")) {
            return new UserPageAction();
        }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ChoiceViewedCountersAction();
        }

        inputOutput.put("Показания счётчика " + types.get(selectedNumber) + ":");
        String login = session.getLogin();
        String type = types.get(selectedNumber);

        session.getModelRecordSet().getRecordSetListByUserAndType(login, type).stream()
//                        .filter(record -> record.getLogin().equals(session.getModelUsers().getUser(session.getLogin())))
//                        .filter(record -> record.getCounterType().equals(types.get(selectedNumber)))
                        .map(Record::getCounterValue)
                        .forEach(counterValue -> inputOutput.put(counterValue.getDate() + " | " + counterValue.getValue()));
        session.addEvent("Просмотрел показания счётчика " + types.get(selectedNumber));

        return this;
    }
}
