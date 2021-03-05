package com.zyk.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPool {

    public static void main(String[] args) {
        //newScheduledThreadPool 创建一个定长线程池,支持定时及周期性任务执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i ++) {
            final int finalI = i;
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println(finalI +"delay 30 seconds");
                }
            }, 30, TimeUnit.SECONDS);
        }
    }
}
