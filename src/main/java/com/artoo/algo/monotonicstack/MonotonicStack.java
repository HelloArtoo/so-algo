package com.artoo.algo.monotonicstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 一个数组，找到左边第一个比它小的，和右边第一个比他小的
 */
public class MonotonicStack {

    //无重复
    //单调栈  3,4,5,2
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                ans[pop][0] = (stack.isEmpty() ? -1 : stack.peek());
                ans[pop][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop][0] = (stack.isEmpty() ? -1 : stack.peek());
            ans[pop][1] = -1;
        }

        return ans;
    }


    //有重复
    public static int[][] getNearLess(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer j : pop) {
                    ans[j][0] = left;
                    ans[j][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                List<Integer> lst = new ArrayList<>();
                lst.add(i);
                stack.push(lst);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer j : pop) {
                ans[j][0] = left;
                ans[j][1] = -1;
            }
        }

        return ans;
    }

//    public static int[][] getNearLess3(int[] arr) {
//        int[][] res = new int[arr.length][2];
//        // List<Integer> -> 放的是位置，同样值的东西，位置压在一起
//        Stack<List<Integer>> stack = new Stack<>();
//        for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
//            // 底 -> 顶， 小 -> 大
//            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
//                List<Integer> popIs = stack.pop();
//                // 取位于下面位置的列表中，最晚加入的那个
//                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
//                for (Integer popi : popIs) {
//                    res[popi][0] = leftLessIndex;
//                    res[popi][1] = i;
//                }
//            }
//            // 相等的、比你小的
//            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
//                stack.peek().add(Integer.valueOf(i));
//            } else {
//                ArrayList<Integer> list = new ArrayList<>();
//                list.add(i);
//                stack.push(list);
//            }
//        }
//        while (!stack.isEmpty()) {
//            List<Integer> popIs = stack.pop();
//            // 取位于下面位置的列表中，最晚加入的那个
//            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
//            for (Integer popi : popIs) {
//                res[popi][0] = leftLessIndex;
//                res[popi][1] = -1;
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        int[] arr = {4, 9, 1, 7, 3, 8};

        int[][] nearLessNoRepeat = getNearLessNoRepeat(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(nearLessNoRepeat[i]));
        }
        System.out.println("------------------------------");

        int[] arr2 = {4, 9, 4, 7, 1, 8, 8, 1, 1, 7, 3, 8};

        int[][] nearLess = getNearLess(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(Arrays.toString(nearLess[i]));
        }
    }
}
