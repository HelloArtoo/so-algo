package com.artoo.algo.sort.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeapQueue {

    public static void main(String[] args) {

        //小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();


        //大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });


    }
}
