package com.localhost;

import com.localhost.in.AdminException;
import com.localhost.model.Tools;
import com.localhost.view.*;
import com.localhost.view.actions.FirstAction;

public class Main {
    public static void main(String[] args) {
        IConsoleUI consoleUI = new ConsoleUI();
//        try {
//            consoleUI.getSession().getAdminSession().addAdmin("admin", "admin");
//        } catch (AdminException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            consoleUI.getSession().getAdminSession().createCounter("Отопление");
//            consoleUI.getSession().getAdminSession().createCounter("Горячая вода");
//            consoleUI.getSession().getAdminSession().createCounter("Холодная вода");
//        } catch (AdminException e) {
//            throw new RuntimeException(e);
//        }
        consoleUI.start(new FirstAction());
//        System.out.println(Tools.getMD5Hash("admin"));
    }
}