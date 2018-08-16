package com.singgel.callBackAsync;

/**
 * 异步回调
 * 提问者A有个问题"1+1=?"，于是A打电话给回答者B，B说他现在很忙，忙完了才能给他想答案，
 * A心想我不能这么一直等着把，于是说：“那咱们约定好，B你想出答案了以打电话的形式告诉我”，
 * 挂了电话A也去忙他自己的事了，过了一会B想出答案按A约定好的方式打电话告诉了B答案
 */
public class MainTest {
    public static void main(String args[]){
        /**
         * 实例化回答者B
         */
        B b = new B();
        /**
         * 实例化提问者A
         */
        A a = new A(b);
        /**
         * A向B提问，开始
         */
        a.call("1 + 1 = ?");
    }
}
