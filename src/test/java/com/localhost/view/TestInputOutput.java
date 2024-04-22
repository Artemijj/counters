package com.localhost.view;

public class TestInputOutput implements IInputOutput{

    private String[] result;
    private int count = 0;
    private String message;

//    public TestInputOutput() {
//        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    }

    public TestInputOutput(String... result) {
        this.result = result;
        message = "";
    }

    @Override
    public String get() {
        String res = result[count];
        count++;
        return res;
    }

    @Override
    public void put(String txt) {
        message = txt;
    }

    public String getMessage() {
        return message;
    }
}
