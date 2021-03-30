package com.artoo.algo.recursion;

/**
 * 1  到 n-1 左边移动到中间
 * 把 n 从左移动到右边
 * 把中间的移动到右边
 */
public class Hanoi {

//
//    public static void leftToRight(int n) {
//        if (n == 1) {
//            System.out.println("Move 1 from left to right");
//            return;
//        }
//        leftToMid(n - 1);
//        System.out.println("Move " + n + " from left to right");
//        midToRight(n - 1);
//    }
//
//    private static void midToRight(int n) {
//    }
//
//    private static void leftToMid(int n) {
//        if(n ==1) {
//            System.out.println("Move 1 from left to mid");
//            return;
//        }
//
//        leftToRight(n-1);
//        System.out.println("Move " +n + " from left to mid" );
//        rightToMid(n-1);
//    }
//
//    private static void rightToMid(int n) {
//        if(n ==1) {
//            System.out.println("Move 1 from right to mid");
//            return;
//        }
//
//        leftToRight(n-1);
//        System.out.println("Move " +n + " from right to mid" );
//        rightToMid(n-1);
//    }....


    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move " + n + " from: " + from + " to: " + to);
            return;
        }

        func(n - 1, from, other, to);
        System.out.println("Move " + n + " from: " + from + " to: " + to);
        func(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        int n = 3;
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }


}
