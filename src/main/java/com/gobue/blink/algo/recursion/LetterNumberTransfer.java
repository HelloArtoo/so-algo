package com.gobue.blink.algo.recursion;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class LetterNumberTransfer {

    //暴力递归
    public static int transfer(String numbers) {
        if (numbers != null && numbers.length() > 0) {
            return process(numbers.toCharArray(), 0);
        }

        return 0;
    }

    private static int process(char[] arr, int i) {
        if (i == arr.length) {
            return 1;
        }

        if (arr[i] == '0') {
            return 0;
        }

        if (arr[i] == '1') {
            int ans = process(arr, i + 1);
            if (i + 1 < arr.length) {
                ans += process(arr, i + 2);
            }
            return ans;
        }

        if (arr[i] == '2') {
            int ans = process(arr, i + 1);
            if (i + 1 < arr.length && arr[i + 1] >= '0' && arr[i + 1] <= '6') {
                ans += process(arr, i + 2);
            }
            return ans;

        }

        return process(arr, i + 1);
    }

    //------------------------------------------------------------------------------------------

    public static int dpWays(String numbers) {
        if (numbers == null && numbers.length() == 0) {
            return 0;
        }
        char[] arr = numbers.toCharArray();
        int len = arr.length;
        int[] dp = new int[len + 1];
        dp[len] = 1;

        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] == '1') {
                int ans = dp[i + 1];
                if (i + 1 < arr.length) {
                    ans += dp[i + 2];
                }
                dp[i] = ans;
            } else if (arr[i] == '2') {
                int ans = dp[i + 1];
                if (i + 1 < arr.length && arr[i + 1] >= '0' && arr[i + 1] <= '6') {
                    ans += dp[i + 2];
                }
                dp[i] = ans;

            } else {
                dp[i] = dp[i + 1];
            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(transfer("11111"));
        System.out.println(dpWays("11111"));


    }

}
