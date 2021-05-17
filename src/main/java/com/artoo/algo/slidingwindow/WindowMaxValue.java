package com.artoo.algo.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class WindowMaxValue {

    public static int[] max(int[] arr, int k) {
        int len = arr.length;
        if (len < k) {
            return null;
        }

        int[] ans = new int[len - k + 1];
        int index = 0;
        //双端队列
        LinkedList<Integer> lst = new LinkedList<>();
        for (int r = 0; r < len; r++) {

            //队列单调递减
            while (!lst.isEmpty() && arr[lst.peekLast()] <= arr[r]) {
                lst.pollLast();
            }
            //里面放的坐标
            lst.addLast(r);

            if(lst.peekFirst() == r - k) {
                lst.pollFirst();
            }

            if (r >= k - 1) {
                //头部就是最大值
                ans[index++] = arr[lst.peekFirst()];
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(max(arr, 3)));
    }
}
