package com.artoo.algo.string;

/**
 * 判断两个字符串是否互为旋转词
 */
public class RotateString {


    /**
     * 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
     * <p>
     * <p>
     * 思路：判断B是否在 "A+A"里面存在
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean rotateString(String A, String B) {
        if (A == null || B == null || A.length() < 1 || B.length() < 1 || A.length() != B.length()) {
            return false;
        }

        String newA = A + A;
        return newA.contains(B);
    }

    public static void main(String[] args) {
        String A = "abcde";
        String B = "cdeab";
        System.out.println(rotateString(A, B));
    }
}
