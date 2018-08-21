package com.singgel.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UnBoundedThreadPool implements Runnable {

    public Integer count;

    public UnBoundedThreadPool(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("任务" + count);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                workQueue
        );
        for (int i = 1; i <= 20; i++) {
            pool.execute(new UnBoundedThreadPool(i));
        }
        Thread.sleep(1000);
        System.out.println("线程池中队列中的线程数量：" + workQueue.size());
        pool.shutdown();
    }
}
