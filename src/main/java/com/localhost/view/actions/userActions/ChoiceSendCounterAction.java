package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.*;
import com.localhost.model.Record;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.util.Date;

public class ChoiceSendCounterAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        CounterType[] types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.length;

        inputOutput.put("Выберите тип счётчика, для передачи показаний.");

        for (int i = 0; i <= arrLength - 1; i++) {
            inputOutput.put(i + " - " + types[i].getCounterTypeName());
        }
        inputOutput.put("p - Выход на предыдущий экран.");

        String read = inputOutput.get();

        if (read.equals("p")) {
            return new UserPageAction();
        }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ChoiceSendCounterAction();
        }

        inputOutput.put("Введите показание счётчика (целое число).");
        int value = Integer.parseInt(inputOutput.get());
        int id = session.getModelRecordSet().nextId();
//        User user = session.getModelUsers().getUser(session.getLogin());
        String counterType = types[selectedNumber].getCounterTypeName();
        CounterValue counterValue = new CounterValue(new Date(), value);
        boolean result = session.getModelRecordSet().addRecord(new Record(id, session.getLogin(), counterType, counterValue));
        if (result) {
            session.addEvent("Передал показания счётчика " + types[selectedNumber].getCounterTypeName());
        } else {
            inputOutput.put("Показания можно передавать один раз в месяц.");
        }

        return this;
    }
}