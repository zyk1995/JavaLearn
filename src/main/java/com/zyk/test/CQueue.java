package com.zyk.test;

import java.util.Stack;

public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;


    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if  (stack1.empty()) {
            if (stack2.empty()) {
                return -1;
            }

        }
        while (!stack1.empty()) {
            int v = stack1.pop();
            stack2.push(v);
        }
        return stack2.pop();
    }
}
