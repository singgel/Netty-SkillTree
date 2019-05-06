package com.singgel.dubboRmi;

import java.rmi.RemoteException;

/**
 * 建立SearchServiceImpl实现远程接口，注意此为远程对象实现类，需要继承UnicastRemoteObject
 */
public class SearchServiceImpl implements SearchService{

    /**
     * 抛出RemoteException
     * @throws RemoteException
     */
    public SearchServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public User findUser(String id) throws RemoteException {
        /**
         * 模拟查找返回数据
         */
        User user=new User(id,"Tom","18岁");
        return user;
    }

}