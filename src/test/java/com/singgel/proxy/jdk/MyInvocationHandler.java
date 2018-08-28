package com.singgel.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理是jdk原生就支持的一种代理方式，
 * 它的实现原理，就是通过让target类和代理类实现同一接口，代理类持有target对象，来达到方法拦截的作用
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    MyInvocationHandler(){
        super();
    }

    MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getName".equals(method.getName())){
            System.out.println("++++++before " + method.getName() + "++++++");
            Object result = method.invoke(target,args);
            System.out.println("++++++after " + method.getName() + "++++++");
            return result;
        }
        else {
            Object result = method.invoke(target,args);
            return result;
        }
    }

}
