package com.singgel.proxy;

public class UserServiceImpl implements UserService {

    @Override
    public String getName(int id) {
        System.out.println("-------getName-------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("-------getAge-------");
        return 10;
    }

}
