package com.gobue.blink.algo.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印字符串的所有组合，比如 abc：
 * a
 * b
 * c
 * ab
 * ac
 * bc
 * abc
 */
public class AllCombinations {


    public static List<String> rec(String s) {
        List<String> lst = new ArrayList<>();
        if (s.length() == 0) {
            return lst;
        }

        if (s.length() == 1) {
            lst.add(s);
            return lst;
        }


        String letter = String.valueOf(s.charAt(0));
        lst.add(letter);
        List<String> subList = rec(s.substring(1));
        for (String str : subList) {
            lst.add(str);
            lst.add(letter + str);
        }
        return lst;
    }

    public static void main(String[] args) {
        String s = "abcde";
        List<String> rec = rec(s);
        System.out.println(rec.size());
        System.out.println(rec);
    }
}
