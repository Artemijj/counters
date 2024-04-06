package com.localhost.view;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.model.CounterType;

import java.util.Arrays;

public class ViewActualCountersAction implements IAction{
    @Override
    public IAction execute(IUserSession session, IInputOutput inputOutput) {
        CounterType[] counterTypes;
        try {
            counterTypes = session.userCounters();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(counterTypes).forEach(type -> System.out.println(type.getCounterTypeName() + "\n" + session.getLastValue(type).getDate() + " | " + session.getLastValue(type).getValue()));
        session.addEvent("Просмотрел актуальные показания всех счётчиков.");

        System.out.println("Выберите следующее действие:\n" +
                "1 - Для выхода на предыдущий экран.\n" +
                "2 - Для выхода из своей сессии.");

        switch (inputOutput.get()) {
            case "1": return new UserPageAction();
            case "2": return new FirstAction();
        }

        return null;
    }
}
