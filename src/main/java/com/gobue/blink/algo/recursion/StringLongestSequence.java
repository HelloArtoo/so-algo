package com.gobue.blink.algo.recursion;

/**
 * 两个字符串的最长公共子序列
 */
public class StringLongestSequence {


    //4个模式，dp[i][j]
    public static int lcse(char[] str1, char[] str2) {

        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            if (flag) {
                dp[i][0] = 1;
            } else {
                if (str1[i] == str2[0]) {
                    flag = true;
                }
                dp[i][0] = flag ? 1 : 0;
            }
        }

        flag = false;
        for (int j = 0; j < M; j++) {
            if (flag) {
                dp[0][j] = 1;
            } else {
                if (str1[0] == str2[j]) {
                    flag = true;
                }
                dp[0][j] = flag ? 1 : 0;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        String a = "a1b2c3d";
        String b = "e1f2g3";
        System.out.println(lcse(a.toCharArray(), b.toCharArray()));
    }

}
