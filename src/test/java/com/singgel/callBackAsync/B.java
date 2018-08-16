package com.singgel.callBackAsync;

/**
 * 回答者B类
 */
public class B {
    /**
     * 回答者B接电话，听问题 这里以调用回答者B的answer方法表示，传入回调方法类、问题参数，以表示谁打的电话，问啥了
     */
    public void answer(CallBack callBack, String question) {
        System.out.println("A调用B的接电话方法：我是回答者B，提问者A问的问题是：" + question);
        /**
         * 模拟回答者B先忙自己的事
         */
        System.out.println("我是回答者B，我接完电话先去忙我自己的事！");
        for (int i = 0; i < 100000; i++) {

        }
        String result = "2";
        System.out.println("我是回答者B，我知道了答案是：" + result);
        /**
         * 调用回调函数，打电话告知A答案是什么
         */
        callBack.callBack(result);
    }
}

