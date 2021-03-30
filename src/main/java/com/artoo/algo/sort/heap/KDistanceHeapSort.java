package com.artoo.algo.sort.heap;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，
 * 每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class KDistanceHeapSort {

    /**
     * 1、构造K个数的最小堆，0-(k-1)最小的数
     * 2、弹出0位置的，就是最小的，然后1-k位置，2-(k+1)位置
     * 3、
     *
     * @param arr
     */
    public static void sort(int[] arr, int k) {
        //前k个数构造小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int i;
        for (i = 0; i <= Math.min(k, arr.length - 1); i++) {
            queue.offer(arr[i]);
        }

        int j = 0;
        for (; i < arr.length; i++) {
            arr[j++] = queue.poll();
            queue.offer(arr[i]);
        }

        while (!queue.isEmpty()) {
            arr[j++] = queue.poll();
        }

    }


    public static void main(String[] args) {

    }
}
