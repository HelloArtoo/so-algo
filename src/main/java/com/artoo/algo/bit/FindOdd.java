package com.gobue.blink.algo.bit;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class FindOdd {

    public static int findOdd(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }

        return xor;

    }


    public static void main(String[] args) {

        int[] arr = {2, 4, 7, 2, 4, 5, 2, 2, 5};
        System.out.println(findOdd(arr));
    }
}
