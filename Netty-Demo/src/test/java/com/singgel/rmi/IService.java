package com.singgel.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {
    //声明服务器端必须提供的服务
    String service(String content) throws RemoteException;
}
