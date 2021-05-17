package com.artoo.algo.string;

import com.artoo.utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 问题描述：给你一个整型数组，返回其中第K小的数
 */
public class FindKth {


    //---------------------------------------------------------------------------------

    //大根堆的解法，nlogK
    //0...k...i 的解法，排除到k...i的大值，堆顶就是k个最小里最大的
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //先维护一个K大的大根堆
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }

        //排除掉i - k大的数
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        //堆顶就是剩下K个里最大的 =  第K小的数
        return maxHeap.peek();
    }

    //---------------------------------------------------------------------------------

    //快排序的思路
    public static int minKth2(int[] arr, int k) {
        int[] array = ArrayUtil.copyArray(arr);
        return processQuickSort(array, 0, array.length - 1, k - 1);
    }

    private static int processQuickSort(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }

        int pivot = arr[L + (int) Math.random() * (R - L + 1)];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return processQuickSort(arr, L, range[0] - 1, index);
        } else {
            return processQuickSort(arr, range[1] + 1, R, index);
        }
    }

    //荷兰国旗问题
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
    //---------------------------------------------------------------------------------

    //快排序的思路
    public static int minKth3(int[] arr, int k) {
        int[] array = ArrayUtil.copyArray(arr);
        return bfprt(array, 0, array.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }

        //bfprt核心是找到pivot这个点
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    //中位数中的中位数
    private static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        //五友团
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    //---------------------------------------------------------------------------------


    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 9};
        System.out.println(minKth1(arr, 4));
        System.out.println(minKth2(arr, 4));

    }

}
