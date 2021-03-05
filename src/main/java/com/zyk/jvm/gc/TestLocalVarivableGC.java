package com.zyk.jvm.gc;


// https://mp.weixin.qq.com/s/rPwj9ClPORiltlIQowYWYA

import com.zyk.jvm.entity.Test;

import java.util.ArrayList;
import java.util.List;

// vm options: -XX:+PrintGCDetails -XX:+PrintGC -Xms20M -Xmx20M
public class TestLocalVarivableGC {

    public static void main(String[] args) {

        //“不使用的对象应手动赋值为null“时大胆去用，但不应当对其有过多依赖，更不能当作是一个普遍规则来推广。
//        gc1();
//        gc2();
//        gc3();
//        gcLoop1();
        OOM_HOLD();
        //OOM_NEW();
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

    private static void gcLoop1() {

        for (int i = 0; i < 600000000; i++) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            //System.out.println(placeHolder.length / 1024);
        }

        System.gc();
    }

    private static void OOM_HOLD() {
        List<Test> list = new ArrayList<>();
        while (true) {
            list.add(new Test());
        }
    }

    private static void OOM_NEW() {
        while (true) {
            Test test = new Test();
            test = null;
        }
    }
}
