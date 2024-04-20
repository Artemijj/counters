package com.localhost.view;

public class TestInputOutput implements IInputOutput{

    private String result;

    public TestInputOutput(String result) {
        this.result = result;
    }

    @Override
    public String get() {
        return result;
    }

    @Override
    public void put(String message) {

    }
}
