package com.artoo.algo.string;

//核心是根据match串的前缀后缀生成next数组。
//当匹配失败的时候，直接跳到next数组的位置
public class Kmp {

    public static int findByKmp(String str, String match) {
        if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }

        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();
        int x = 0, y = 0;
        int[] next = getNextArray(matchArr);
        while (x < strArr.length && y < matchArr.length) {
            if (strArr[x] == matchArr[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        System.out.println("x:" + x + "y:" + y);
        return y == matchArr.length ? x - y : -1;
    }

    //核心是生成next数组,而且是快速的生成

    /**
     * i - 1位置的信息如何退出i的信息
     * cn : 代表next[i-1]+1的位置
     *
     * @param arr
     * @return
     */
    public static int[] getNextArray(char[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int x = 2;
        //cn : 代表next[i-1]+1的位置
        // cn代表，cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        while (x < next.length) {
            //相等
            if (arr[x - 1] == arr[cn]) {
                next[x++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[x++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "abasabatabasabasz";
        String m = "tabasaba";
        System.out.println(findByKmp(s, m));
    }

}
