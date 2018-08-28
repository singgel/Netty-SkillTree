package com.singgel.threadPool;

public class ThreadPoolTest {

    public static void main(String[] agrs){
        // 创建3个线程的线程池
        ThreadPool threadPool = ThreadPool.getThreadPool(3);
        threadPool.execute(new Runnable[]{new Task(),new Task(),new Task()});
        threadPool.execute(new Runnable[]{new Task(),new Task(),new Task()});
        System.out.println(threadPool);
        threadPool.destroy();
        System.out.println(threadPool);
    }

    static class Task implements Runnable{
        private static volatile int i = 1;

        // 执行任务
        @Override
        public void run() {
            System.out.println("任务 " + (i++) + " 完成");
        }
    }
}
