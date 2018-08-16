package com.singgel.callBackSync;

public class MainTest {
    public static void main(String[] args) {
        Caller caller = new Caller();
//		caller.setCallFunc(new Client());
        caller.setCallFunc(new MyCallInterface() {
            public void printName() {
                System.out.println("This is the client printName method");
            }
        });
        caller.call();
    }
}
