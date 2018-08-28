package com.singgel.proxy.cglib;

import com.hks.eightsortingalgorithms.proxy.UserService;
import com.hks.eightsortingalgorithms.proxy.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyTest {

    public static void main(String[] args) {
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(myMethodInterceptor);

        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }

}
