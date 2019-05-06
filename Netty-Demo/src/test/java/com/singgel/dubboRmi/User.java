package com.singgel.dubboRmi;

import java.io.Serializable;

/**
 * 为服务建立一个Model层，此对象需要远程传输，
 * 所以必须实现implements Serializable序列化接口，
 * 也就是可以在client和server端进行传输的可序列化对象
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String age;

    public User(String id,String name,String age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("~用户id-"+id);
        sb.append("~用户姓名-"+id);
        sb.append("~用户年龄-"+id);
        return sb.toString();
    }

}