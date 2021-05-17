package com.artoo.algo.array.sum;


/**
 * 一个正负数组，返回最长的子数组，子数组的累加和 = k
 *
 * 思路：
 *   i...len循环
 *   每次求当前累加的和 s ， 然后求（s - k）最早出现的位置
 *    s-k最早出现的位置放map中进行首次定位（出事化的时候默认添加 0, -1的KV）
 *    每次比较最长的结果
 */
public class LongestSumSubArrayLength {
}
