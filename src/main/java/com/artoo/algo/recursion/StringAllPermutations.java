package com.artoo.algo.recursion;

public class StringAllPermutations {

    public static void printStringAllPermutations(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        process(s.toCharArray(), 0);
    }

    private static void process(char[] arr, int i) {
        if (i == arr.length) {
            System.out.println(String.valueOf(arr));
            return;
        }

        //去重复的话，可以用个记录表
        boolean[] visit = new boolean[26];
        for (int j = i; j < arr.length; j++) {
            if (!visit[arr[j] - 'a']) {
                visit[arr[j] - 'a'] = true;
                swap(arr, i, j);
                process(arr, i + 1);
                swap(arr, i, j);
            }

        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        printStringAllPermutations("aba");
    }
}
