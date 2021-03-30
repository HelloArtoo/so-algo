package com.gobue.blink.algo.collection;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    Queue<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        list = new LinkedList<Integer>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        list.offer(x);
        for (int i = 0; i < list.size() - 1; i++) {
            list.offer(list.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return list.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return list.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return list.isEmpty();
    }
}
