package com.singgel.rmi;


/*
 * Context接口表示一个命名上下文，它由一组名称到对象的绑定组成。
 * 它包含检查和更新这些绑定的一些方法。
 */
import javax.naming.Context;
/*
 * InitialContext类是执行命名操作的初始上下文。
 * 该初始上下文实现 Context 接口并提供解析名称的起始点。
 */
import javax.naming.InitialContext;

public class Server {
    public static void main(String[] args) {
        try {
            //实例化实现了IService接口的远程服务ServiceImpl对象
            IService service02 = new ServiceImpl("service02");
            //初始化命名空间
            Context namingContext = new InitialContext();
            //将名称绑定到对象,即向命名空间注册已经实例化的远程服务对象
            namingContext.rebind("rmi://127.0.0.1:8787/service02", service02);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("服务器向命名表注册了1个远程服务对象！");
    }
}
