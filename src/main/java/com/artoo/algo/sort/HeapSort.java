package com.artoo.algo.sort;

import java.util.Arrays;

/**
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，
 * 此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 * 如此反复执行，便能得到一个有序序列了
 * <p>
 * <p>
 * 与堆有关的两个核心方法
 * 两个核心的方法,heapInsert, heapify
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = {7, 4, 8, 1, 1, 9, 6, 2, 3, 7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int len = arr.length;
        //方式1：先构成一个大顶堆 n * log(n)
//        for (int i = 1; i < len; i++) { //o(n)
//            heapInsert(arr, i); //log(n)
//        }
        //方式2：0（n） , 如果仅仅需要把一个数组变成大顶堆，优化成o(n)的复杂度
        for (int i = len - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int ans = len;
        for (int i = 0; i < len; i++) {
            //堆顶与末尾元素交换，此时末尾就是最大值
            swap(arr, 0, --ans);
            //剩余n-1重新heapify，得到n-1个元素中的最大值
            heapify(arr, 0, ans);
        }
    }


    /**
     * 父节点：(i - 1)/2
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index) {
//        int theRoot;
//        while (index > (theRoot = (index - 1) / 2)) {
//            if (arr[index] < arr[theRoot]) {
//                break;
//            }
//            swap(arr, index, theRoot);
//            index = theRoot;
//        }

        int theRoot;
        while (arr[index] > arr[theRoot = (index - 1) / 2]) {
            swap(arr, index, theRoot);
            index = theRoot;
        }
    }


    /**
     * 大堆化
     * <p>
     * 将index 到 heapSize 这段位置的数变成大根堆
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int right = left + 1;
            int max = right < heapSize && arr[right] > arr[left] ? right : left;
            max = arr[index] < arr[max] ? max : index;
            if (max == index) {//不需要交换
                break;
            }
            swap(arr, index, max);
            index = max;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }


    //最大堆
    public static class MaxHeap {

        private int[] heap;
        private int size;
        private int limit;

        MaxHeap(int limit) {
            this.size = 0;
            this.limit = limit;
            heap = new int[limit];
        }

        public void push(int num) {
            if (size == limit) {
                throw new RuntimeException("the MaxHeap is full.");
            }

            heap[size] = num;
            heapInsert(heap, size++);
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }

            int top = heap[0];
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return top;
        }

        public int resign() {
            //
            return 0;
        }
    }
}
