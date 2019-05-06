package com.singgel.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoundedThreadPool implements Runnable {


    public String name;

    public BoundedThreadPool(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1, //核心池的大小。 当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中
                2,	//线程池最大线程数，它表示在线程池中最多能创建多少个线程
                1L,  //表示线程没有任务执行时最多保持多久时间会终止
                ///TimeUnit.DAYS;               //天
                //TimeUnit.HOURS;             //小时
                //TimeUnit.MINUTES;           //分钟
                //TimeUnit.SECONDS;           //秒
                //TimeUnit.MILLISECONDS;      //毫秒
                //TimeUnit.MICROSECONDS;      //微妙
                //TimeUnit.NANOSECONDS;       //纳秒
                TimeUnit.SECONDS,  //参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
                ///ArrayBlockingQueue;
                //LinkedBlockingQueue;
                //SynchronousQueue
                workQueue //一个阻塞队列，用来存储等待执行的任务
        );
        threadPool.execute(new BoundedThreadPool("任务1"));
        threadPool.execute(new BoundedThreadPool("任务2"));
        threadPool.execute(new BoundedThreadPool("任务3"));
        threadPool.execute(new BoundedThreadPool("任务4"));
        threadPool.execute(new BoundedThreadPool("任务5"));
        threadPool.execute(new BoundedThreadPool("任务6"));
        threadPool.shutdown();
    }


}