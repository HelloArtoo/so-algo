package com.gobue.blink.algo.recursion;

/**
 * 枚举的方式
 */
public class CoinsWay {

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    /**
     * @param arr
     * @param i   从第 i 种钱开始
     * @param aim 剩余金额
     * @return
     */
    private static int process(int[] arr, int i, int aim) {
        if (aim < 0) {
            return 0;
        }

        if (i == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhangshu = 0; zhangshu * arr[i] <= aim; zhangshu++) {
            ways += process(arr, i + 1, aim - zhangshu * arr[i]);
        }

        return ways;
    }

    //------------------------------------------------------------------------------------------

    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }

        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(ways(arr, sum));
        System.out.println(ways2(arr, sum));
    }
}
