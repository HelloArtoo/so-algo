package com.artoo.algo.bit;

/**
 * 对于一个输入的int整型，保留其二进制存储中的最后一个1，其余的全部置为0，然后再以int型输出
 */
public class FindRightBitOne {

    public static int findRightBitOne(int num) {
        return num & ((~num) + 1);
    }

    public static void main(String[] args) {
        int num = 120;
        System.out.println("before:" + Integer.toBinaryString(num));
        System.out.println("after:" + Integer.toBinaryString(findRightBitOne(num)));
    }
}
