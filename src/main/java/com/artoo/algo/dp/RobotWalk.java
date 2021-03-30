package com.artoo.algo.dp;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {


    public static int walk(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || K < 1 || P < 1 || M > N || P > N) {
            return 0;
        }

        //优化
        //int[][] dp = new int[N + 1][K + 1];
        //init(dp);  //全部初始化为-1
        return process(N, M, K, P);
    }

    private static int process(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }

        if (cur == 1) {
            return process(N, cur + 1, rest - 1, P);
        }

        if (cur == N) {
            return process(N, cur - 1, rest - 1, P);
        }

        return process(N, cur + 1, rest - 1, P) + process(N, cur - 1, rest - 1, P);
    }

    public static void main(String[] args) {

    }
}
