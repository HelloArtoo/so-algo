package com.artoo.algo.array.sum;

/**
 * 数组有正有负，求数组累加和 <= k 的最长子数组
 *
 * 思路：O(N)解法
 *
 * i...后所有的累计和都被抓到了，以及右边界
 *
 * minSum[i]  从i往右扩，最小累加和的值
 * minSumEnd[i] 从i往右扩，最小累加和的值的结束位置为j
 *             1、 从右往左遍历一遍，求所有的值
 *             2、从左往右
 *
 */
public class LongestLessSumSubArrayLength {

}
