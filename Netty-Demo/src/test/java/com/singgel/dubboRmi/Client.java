package com.singgel.dubboRmi;

import java.rmi.Naming;

/**
 * 创建客户端，导入上面的实体类和接口，通过Naming.lookup()的方式调用
 */
public class Client {

    public static void main(String[] args){
        try {
            //调用远程对象，注意RMI路径与接口必须与服务器配置一致
            SearchService searchService=(SearchService) Naming.lookup("rmi://127.0.0.1:5678/searchService");
            User user=searchService.findUser("100");
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
