package com.singgel.callBackAsync;

/**
 * 这是一个回调接口，里面定义的方法就是回调函数
 */
public interface CallBack {
    /**
     * 这是一个回调函数，用于回答者B知道答案后给提问者A回电话，并告知提问者A答案是什么
     * 这个回电话的方式callBack是提问者A确定的，所以这个方法的实现类是A类
     * 这个回电话的内容result是回答者B提供的，所以这个变量的值是在B类中确定的
     */
    void callBack(String result);
}

