package com.gobue.blink.algo.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 * [ac,bj,sc,ket...]
 * <p>
 * 两个字符串长度一样，当k进制正数的比较
 * 长度不一样，后面补成一样长0（比a assci还小的值）再比较
 */
public class CombineString {


    /**
     * x.y <= y.x 则 x前，y后，x和y拼接
     * <p>
     * 思想：
     * a*m(b) + b <= b* m(a) + a
     * m(b) = a^b次方长度
     *
     * @param strs
     * @return
     */
    public static String combine(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());
        String ans = "";
        for (int i = 0; i < strs.length; i++) {
            ans += strs[i];
        }
        return ans;
    }

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static void main(String[] args) {
        String[] a = {"efg", "cd", "ab", "b"};
        System.out.println(combine(a));
    }

}
