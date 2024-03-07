package com.localhost.model;

import com.localhost.in.ConsoleInput;
import com.localhost.repositories.*;

public class CounterService implements ICounterService{
    private ConsoleInput ci = new ConsoleInput();
    private CounterTypes ct = new CounterTypes();
    private IUsers users = new Users();
    @Override
    public void start() {

    }
}
