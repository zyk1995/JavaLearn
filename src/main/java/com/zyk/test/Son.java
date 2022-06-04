package com.zyk.test;

import org.w3c.dom.ls.LSOutput;

public class Son extends Father{
    static {
        System.out.println("this is Son");
    }

    public static void main(String[] args) {
        new Son();
    }
}
