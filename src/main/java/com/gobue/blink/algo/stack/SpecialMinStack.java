package com.gobue.blink.algo.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * <p>
 * 2）设计的栈类型可以使用现成的栈结构。
 */
public class SpecialMinStack {


    public static class MinStack {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;
         public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public Integer push(Integer num) {
            if (minStack.isEmpty()) {
                minStack.push(num);
            } else if (num < minStack.peek()) {
                minStack.push(num);
            } else {
                minStack.push(minStack.peek());
            }

            dataStack.push(num);
            return num;
        }

        public Integer pop() {
            minStack.pop();
            return dataStack.pop();
        }

        public Integer getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();


    }
}
