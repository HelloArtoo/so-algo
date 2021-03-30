package com.gobue.blink.algo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 */
public class StringSubSequence {


    public static List<String> printStringSubSequence(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return rst;
        }

        process(s.toCharArray(), 0, "", rst);
        return rst;
    }


    private static void process(char[] arr, int i, String path, List<String> ans) {
        if (i == arr.length) {
            ans.add(path);
            return;
        }

        // 不拿
        process(arr, i + 1, path, ans);
        //拿
        process(arr, i + 1, path + arr[i], ans);
    }


    public static void main(String[] args) {
        System.out.println(printStringSubSequence("abc"));
    }
}
