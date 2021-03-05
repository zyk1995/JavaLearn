package com.zyk.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPool {
    public static void main(String[] args) {
        // 线程池是无限大的，当一个线程完成任务之后，这个线程可以接下来完成将要分配的任务，而不是创建一个新的线程
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10;i ++) {
            final int index = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cacheThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });

        }
    }
}
