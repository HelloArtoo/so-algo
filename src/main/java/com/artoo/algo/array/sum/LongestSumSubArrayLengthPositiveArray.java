package com.artoo.algo.array.sum;

/**
 * 正数数组，求累计和 = k的最大结果
 * <p>
 * 答案：滑动窗口
 */
public class LongestSumSubArrayLengthPositiveArray {


    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int max = 10;
        int[] arr = {7, 2, 1, 8, 2, 4, 4, 2, 6};
        System.out.println(getMaxLen(arr, 10));
    }

}
