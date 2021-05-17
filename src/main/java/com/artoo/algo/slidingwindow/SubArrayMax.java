package com.artoo.algo.slidingwindow;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class SubArrayMax {

    /**
     * 条件：最大值 - 最小值 <= 给定的num
     * 两个结论（特别重要）：
     * 1、大范围的L...R如果满足，则小范围的L...R也满足
     * 2、小范围的L...R如果不满足，大范围的L...R也不满足
     * <p>
     * 每次固定求0位置开头的达标数量，0...X（满足条件的最大位置）
     *
     * @param arr
     * @param num
     * @return
     */
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int L = 0;
        int R = 0;
        int res = 0;
        while (L < arr.length) { // L是开头位置，尝试每一个开头
            // 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止
            while (R < arr.length) { // R是最后一个达标位置的再下一个
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                // R -> arr[R],
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                R++;
            }
            // R是最后一个达标位置的再下一个，第一个违规的位置
            res += R - L;
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 3, 8, 1, 9, 6, 7};
        System.out.println(getNum(arr, 3));
    }
}
