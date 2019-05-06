package com.singgel.dubboRmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 建立服务器端，在服务器端注册RMI通讯端口与通讯路径
 */
public class Process {

    public static void main(String[] args) {

        try {
            SearchService searchService = new SearchServiceImpl();
            //注册通信端口
            Registry registry = LocateRegistry.createRegistry(5678);
            //注册通讯路径
            Naming.rebind("rmi://127.0.0.1:5678/searchService", searchService);
            System.out.println("Service Start!");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
