package com.artoo.algo.string;

/**
 * O(N)复杂度实现求解字符串最长回文子串
 */
public class Manacher {


    //最长回文子串求解
    //  //循环求解
    //        // 1、如果i在R外，则左右暴力匹配是否是回文，并更新C、R
    //        // 2、如果i在R内分三种情况(i'是i的对称点)
    //        //      2-1 i'在以C到R为半径的范围内，p[i] = p[i']
    //        //      2-2 i'的范围超过了左半径，则p[i] = R-i
    //        //      2-3 i'正好与左半径重合，p[i] = （R-i） + 暴力外扩
    public static int doManacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        //step1 字符串转成manancher串
        char[] str = manacherString(s);
        //step2 申请回文半径数组p[]，中心点C和最右半径R
        int[] p = new int[str.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != str.length; i++) {
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            p[i] = R > i ? Math.min(p[2 * C - i], R - i) : 1;
            while (i + p[i] < str.length && i - p[i] > -1) {
                if (str[i + p[i]] == str[i - p[i]]) {
                    p[i]++;
                } else {
                    break;
                }
            }

            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }

            max = Math.max(max, p[i]);
        }

        return max - 1;
    }

    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] mArr = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i != mArr.length; i++) {
            //(i & 1) == 0 则为偶数
            mArr[i] = ((i & 1) == 0) ? '#' : chars[index++];
        }

        return mArr;
    }


    public static void main(String[] args) {
        String a  = "123abcba421";
        System.out.println(doManacher(a));

    }

}
