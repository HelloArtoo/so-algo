package com.artoo.algo.bit;

/**
 * 找到int二进制中1的个数
 */
public class FindIntBitOneCnt {

    public static int findIntBitOneCnt(int num) {

        int cnt = 0;
        while (num != 0) {
            //每次移掉最右边的1
            int rightBitOne = num & (~num + 1);
            num ^= rightBitOne;
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {

        int num = 100;
        System.out.println("二进制为：" + Integer.toBinaryString(num) + "，共有 " + findIntBitOneCnt(num) + " 个1。");
    }
}
