package com.singgel.dubboRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 创建远程接口SearchService，接口必须继承Remote类，
 * 每一个定义方法都要抛出RemoteException异常
 */
public interface SearchService extends Remote {

    User findUser(String id) throws RemoteException;

}
