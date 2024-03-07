package com.localhost.view;

public class ConsoleUI implements IConsoleUI{
    @Override
    public void firstScreen() {
        System.out.println("Зарегистрируйтесь или войдите в систему передачи показаний счётчиков.\n" +
                            "Введите:\n" +
                            "1 - для регистрации.\n" +
                            "2 - для входа.");
    }

    @Override
    public void registrationNameScreen() {
        System.out.println("Введите имя.");
    }

    @Override
    public void registrationAddressScreen() {
        System.out.println("Введите адрес.");
    }

    @Override
    public void registrationPhoneScreen() {
        System.out.println("Введите номер телефона.");
    }

    @Override
    public void registrationPasswordScreen() {
        System.out.println("Введите пароль.");
    }

    @Override
    public void loginNameScreen() {
        System.out.println("Введите имя.");
    }

    @Override
    public void loginPasswordScreen() {
        System.out.println("Введите пароль.");
    }

    @Override
    public void countersSelectScreen() {
        System.out.println("Выберите из списка имеющиеся у Вас счётчики.\n" +
                            "Введите:\n" +
                            "1 - счётчик отопления.\n" +
                            "2 - счётчик холодной воды.\n" +
                            "3 - счётчик горячей воды.\n" +
                            "0 - для перехода на предыдущий экран.");
    }

    @Override
    public void userHomeScreen() {
        System.out.println("1 - Регистрация счётчиков.\n" +
                            "2 - Передача показаний счётчиков.\n" +
                            "3 - Просмотр переданных показаний.\n" +
                            "4 - Выход из личного кабинета.");
    }

    @Override
    public void transmissionOfReadingsScreen() {
        System.out.println("Выберите счётчик для передачи показаний.\n" +
                            "Введите:\n" +
                            "1 - счётчик отопления.\n" +
                            "2 - счётчик холодной воды.\n" +
                            "3 - счётчик горячей воды.\n" +
                            "0 - для перехода на предыдущий экран.");
    }

    @Override
    public void viewReadingsScreen() {
        System.out.println("Выберите счётчик для просмотра показаний.\n" +
                            "Введите:\n" +
                            "1 - счётчик отопления.\n" +
                            "2 - счётчик холодной воды.\n" +
                            "3 - счётчик горячей воды.\n" +
                            "0 - для перехода на предыдущий экран.");
    }

    @Override
    public void adminViewReadingsScreen() {
        System.out.println("Введите имя пользователя, показания которого Вы хотите просмотреть.\n" +
                            "Введите:\n" +
                            "0 - для перехода на предыдущий экран.");
    }
}
