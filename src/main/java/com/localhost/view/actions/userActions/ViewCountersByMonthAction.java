package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.CounterValue;
import com.localhost.model.Tools;
import com.localhost.view.IInputOutput;
import com.localhost.view.actions.IAction;

import java.time.ZoneId;
import java.util.Arrays;

public class ViewCountersByMonthAction implements IAction {
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        String[] types;
        try {
            types = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        int arrLength = types.length;

        inputOutput.put("Выберите тип счётчика, для просмотра показаний.");

        for (int i = 0; i <= arrLength - 1; i++) {
            inputOutput.put(i + " - " + types[i]);
        }
        inputOutput.put("p - Выход на предыдущий экран.");

        String read = inputOutput.get();

        if (read.equals("p")) {
            return new UserPageAction();
        }

        int selectedNumber = Tools.parse(read);

        if (selectedNumber >= arrLength || selectedNumber == Integer.MAX_VALUE) {
            inputOutput.put("Введите корректный номер счётчика.");
            return new ViewCountersByMonthAction();
        }

        inputOutput.put("Введите год, в формате XXXX.");
        int year = Tools.parse(inputOutput.get());
        inputOutput.put("Введите номер месяца.");
        int month =  Tools.parse(inputOutput.get());

        CounterValue[] values;

        try {
            values = session.getCounterValues(types[selectedNumber]);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }

        inputOutput.put("Счётчик - " + types[selectedNumber] + ":");

        Arrays.stream(values)
                .filter(counterValue ->  counterValue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
                .filter(counterValue ->  counterValue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month)
                .forEach(counterValue -> inputOutput.put(counterValue.getDate() + " | " + counterValue.getValue()));
        session.addEvent("Просмотрел показания счётчика " + types[selectedNumber]);

        return new ViewCountersByMonthAction();
    }
}
