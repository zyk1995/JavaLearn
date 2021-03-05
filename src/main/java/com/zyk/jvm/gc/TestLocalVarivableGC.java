package com.zyk.jvm.gc;


// https://mp.weixin.qq.com/s/rPwj9ClPORiltlIQowYWYA

import com.zyk.jvm.entity.Test;
import sun.misc.GC;

import java.util.ArrayList;
import java.util.List;

// vm options: -XX:+PrintGCDetails -XX:+PrintGC -Xms20M -Xmx20M -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps
public class TestLocalVarivableGC {

    public static void main(String[] args) throws InterruptedException {

        //“不使用的对象应手动赋值为null“时大胆去用，但不应当对其有过多依赖，更不能当作是一个普遍规则来推广。
//        gc1();
//        gc2();
//        gc3();
        gc4();
//        gcLoop1();
//        gcLoopLoop();
       // OOM_HOLD();
        //OOM_NEW();
        //OOM_NEW1();
    }


    private static void gc1() {
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
        }
        System.gc();
        // placeHolder没有回收
    }


    private static void gc2() {
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
            placeHolder = null;
        }
        System.gc();
        // placeHolder被回收了

    }

    private static void gc3() {
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
        }
        int replacer = 1;
        System.gc();
        // 栈优化，重用placeHolder的slot,placeHolder被回收了

    }

    private static void gc4() throws InterruptedException {
        Test test = new Test();
        Thread.sleep(5000);
        test = null;
        Thread.sleep(5000);
        test = new Test();
        Thread.sleep(5000);

    }

    private static void gcLoop1() {

        for (int i = 0; i < 600000000; i++) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            //System.out.println(placeHolder.length / 1024);
        }

        System.gc();
    }

    private static void gcLoopLoop() {
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 6000; j++) {
                Test test = new Test();
            }

        }
    }

    private static void OOM_HOLD() {
        List<Test> list = new ArrayList<>();
        int count = 0;
        while (true) {
            list.add(new Test());
            count++;
            System.out.println(count);

        }

    }

    private static void OOM_NEW() {
//        GC overhead limit exceeded 当JVM花太多时间执行垃圾回收并且只能回收很少的堆空间时，就会发生该错误
        int count = 0;
        while (true) {
            Test test = new Test();
            test = null;
            count++;
            System.out.println(count);

        }
    }

    private static void OOM_NEW1() {
//        GC overhead limit exceeded 当JVM花太多时间执行垃圾回收并且只能回收很少的堆空间时，就会发生该错误
        int count = 0;
        while (true) {
            Test test = new Test();
            count++;
            System.out.println(count);

        }
    }


}
