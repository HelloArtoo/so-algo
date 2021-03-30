package com.artoo.algo.recursion;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {


        return process(w, v, 0, bag);
    }


    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest <= 0) {
            return 0;
        }

        if (index == w.length) {
            return 0;
        }

        //拿当前那个
        int no = process(w, v, index + 1, rest);

        //不拿当前那个
        int yes = Integer.MIN_VALUE;
        if (rest - w[index] > 0) {
            //当前重量 + 后续最大重量
            yes = v[index] + process(w, v, index + 1, rest - w[index]);
        }

        return Math.max(no, yes);
    }

    //------------------------------------------------------------------------------------------

    public static int dpWays(int[] w, int[] v, int bag) {

        int len = w.length;
        int[][] dp = new int[len + 1][bag + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int rest = 1; rest <= bag; rest++) {
                //拿当前那个
                int no = dp[i + 1][rest];
                //不拿当前那个
                int yes = Integer.MIN_VALUE;
                if (rest - w[i] > 0) {
                    //当前重量 + 后续最大重量
                    yes = v[i] + dp[i + 1][rest - w[i]];
                }
                //return Math.max(no, yes);
                dp[i][rest] = Math.max(no, yes);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWays(weights, values, bag));
    }
}
