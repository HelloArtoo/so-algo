package com.gobue.blink.algo.bit;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class FindOddTwo {

    public static void printOddTwo(int[] arr) {

        //需要找两个奇数A、B
        //首先找到A ^ B = xor;
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }

        //找到xor最右边的1，这个1能将A、B区分开来，将数据分成2份
        int rigthBitOne = xor & (~xor + 1);
        //其中的一份数据再求出xor` 代表A和B其中一个值
        int xorA = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rigthBitOne) != 0) {
                xorA ^= arr[i];
            }
        }

        //另一个值则xor ^ xor`则为另一个值，因为 N ^ N = 0
        System.out.println("两个奇数，[" + xorA + ", " + (xor ^ xorA) + "]");
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 3, 4, 1, 2, 3, 4, 5, 7, 7, 3, 9, 6, 3, 3, 6};
        printOddTwo(arr);
    }

}
