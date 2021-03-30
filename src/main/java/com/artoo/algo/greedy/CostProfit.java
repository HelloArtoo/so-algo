package com.artoo.algo.greedy;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * <p>
 * costs[i]表示i号项目的花费
 * <p>
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * <p>
 * K表示你只能串行的最多做k个项目
 * <p>
 * M表示你初始的资金
 * <p>
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * <p>
 * 输出：你最后获得的最大钱数。
 */
public class CostProfit {

    //成本最小堆,利润最大堆
    public static int findMaximizedCapital(int k, int m, int[] costs, int[] profits) {

        //构建成本小顶堆
        PriorityQueue<Program> minCost = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        for (int i = 0; i < costs.length; i++) {
            minCost.add(new Program(costs[i], profits[i]));
        }

        PriorityQueue<Program> maxProfit = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        for (int i = 0; i < k; i++) {
            //构建利润大顶堆
            while (minCost.peek() != null && minCost.peek().cost <= m) {
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()) {
                break;
            }
            m += maxProfit.poll().profit;
        }

        return m;
    }


    @AllArgsConstructor
    static class Program {
        int cost;
        int profit;
    }


    public static void main(String[] args) {

        int[] costs = {300, 50, 100, 60, 250};
        int[] profits = {200, 150, 50, 200, 300};
        System.out.println(findMaximizedCapital(2, 100, costs, profits));
    }
}
