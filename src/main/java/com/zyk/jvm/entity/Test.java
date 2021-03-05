package com.zyk.jvm.entity;

public class Test {
    public Test() {
        System.out.println("创建对象"+ this);
    }

    @Override
    protected void finalize() throws Throwable {
        System.err.println("销毁对象" + this);

    }
}
