package com.gobue.blink.algo.recursion;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueen {


    //n皇后
    public static int findQ(int n) {
        if (n == 1) {
            return 1;
        }

        if (n < 4) {
            return 0;
        }

        int[] record = new int[n];
        return process(0, record, n);
    }

    private static int process(int index, int[] record, int n) {

        //代表这是一种解法
        if (index == n) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (isValid(record, index, i)) {
                record[index] = i;
                res += process(index + 1, record, n);
            }
        }

        return res;
    }

    //(a,b) (c,d)两个点
    //斜线怎么判断？  |a-b| == |c-d|
    //b == d
    //index 行。i 列
    private static boolean isValid(int[] record, int i, int j) {

        for (int k = 0; k < i; k++) {
            //|a-b| == |c-d|
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    //-------------------------------------------------
    //n皇后
    public static int findQ2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n < 4 || n > 32) {
            return 0;
        }

        int limit = (n == 32 ? -1 : (1 << n - 1));
        return process2(limit, 0, 0, 0);
    }

    //优化
    //用位运算优化常数时间
    //8皇后举例
    private static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        if (limit == colLimit) {
            return 1; // colLimit : 11111111
        }

        //能放的位置，11100111
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int res = 0;
        while (pos != 0) {
            //取最左边的1
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, (colLimit | mostRightOne),
                    (leftDiaLimit | mostRightOne) << 1,
                    (rightDiaLimit | mostRightOne) >>> 1);
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(findQ(4));
        System.out.println(findQ(8));

    }
}
