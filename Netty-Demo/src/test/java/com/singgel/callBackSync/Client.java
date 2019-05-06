package com.singgel.callBackSync;

public class Client implements MyCallInterface {

    @Override
    public void printName() {
        System.out.println("This is the client printName method");
    }

}
