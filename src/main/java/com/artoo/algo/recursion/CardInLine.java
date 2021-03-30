package com.gobue.blink.algo.recursion;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class CardInLine {

    public static int win1(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }

        //先拿和后拿中取最大值
        return Math.max(f(cards, 0, cards.length - 1), s(cards, 0, cards.length - 1));
    }

    //先拿
    private static int f(int[] cards, int i, int j) {
        //拿完了
        if (i == j) {
            return cards[i];
        }

        //先拿左边
        int left = cards[i] + s(cards, i + 1, j);
        //先拿右边
        int right = cards[j] + s(cards, i, j - 1);
        return Math.max(left, right);
    }

    //后拿
    private static int s(int[] cards, int i, int j) {
        if (i == j) {
            return 0;
        }

        int left = f(cards, i + 1, j);
        int right = f(cards, i, j - 1);
        return Math.min(left, right);
    }

    //------------------------------------------------------------------------------------------

    public static int win2(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }

        int len = cards.length;
        int[][] f = new int[len][len];
        int[][] s = new int[len][len];

        for (int i = 0; i < len; i++) {
            //对角线填充
            f[i][i] = cards[i];
        }


        for (int i = 1; i < len; i++) {
            //一开始行在0位置，列在i位置，继续填充对角线
            int l = 0;
            int r = i;
            while (l < len && r < len) {
                f[l][r] = Math.max(cards[l] + s[l + 1][r], cards[r] + s[l][r - 1]);
                s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
                l++;
                r++;
            }
        }


        //先拿和后拿中取最大值
        return Math.max(f[0][len - 1], s[0][len - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }

}
