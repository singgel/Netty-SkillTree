package com.singgel.juc;

import java.util.concurrent.locks.ReentrantLock;

//下面演示一个简单的死锁，两个线程分别占用 south 锁和 north 锁，并同时请求对方占用的锁，导致死锁
public class DeadLock extends Thread{
    protected Object myDirect;
    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();

    public DeadLock(Object obj){
        this.myDirect = obj;
        if(myDirect==south){
            this.setName("south");
        }else{
            this.setName("north");
        }
    }

    @Override
    public void run(){
        if(myDirect==south){
            try{
                north.lockInterruptibly();//占用 north
                try{
                    Thread.sleep(500);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                south.lockInterruptibly();
                System.out.println("car to south has passed");
            }catch(InterruptedException ex){
                System.out.println("car to south is killed");
                ex.printStackTrace();
            }finally{
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(south.isHeldByCurrentThread()){
                    south.unlock();
                }
            }
        }
        if(myDirect==north){
            try{
                south.lockInterruptibly();//占用 south
                try{
                    Thread.sleep(500);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                north.lockInterruptibly();
                System.out.println("car to north has passed");
            }catch(InterruptedException ex){
                System.out.println("car to north is killed");
                ex.printStackTrace();
            }finally{
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(south.isHeldByCurrentThread()){
                    south.unlock();
                }
            }
        }

    }
    public static void main(String[] args) throws InterruptedException{
        DeadLock car2south = new DeadLock(south);
        DeadLock car2north = new DeadLock(north);
        car2south.start();
        car2north.start();
    }
}
