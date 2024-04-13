package com.localhost;

import com.localhost.in.AdminException;
import com.localhost.view.*;
import com.localhost.view.actions.FirstAction;

public class Main {
    public static void main(String[] args) {
        IConsoleUI consoleUI = new ConsoleUI();
//        consoleUI.start(new FirstAction());
//        System.out.println(consoleUI.getSession());
        try {
            consoleUI.getSession().getAdminSession().addAdmin("admin", "admin");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        try {
            consoleUI.getSession().getAdminSession().createCounter("Отопление");
            consoleUI.getSession().getAdminSession().createCounter("Горячая вода");
            consoleUI.getSession().getAdminSession().createCounter("Холодная вода");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(consoleUI.getSession().getModelUsers().getUserList().size());
//        System.out.println(consoleUI.getSession().getModelCounters().getCounterList().size());
        consoleUI.start(new FirstAction());
    }
}