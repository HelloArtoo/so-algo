package com.artoo.algo.tree;

import lombok.AllArgsConstructor;

/**
 * 判断一棵树是否是满二叉树
 */
public class ISFullBTree {


    //核心是， 高度 l 节点数量为 n 的话，只有满二叉树满足 2^l - 1 = n

    public static boolean isFull(TreeNode head) {
        if (head == null) {
            return true;
        }

        Info ans = process(head);
        return ((1 << ans.height) - 1) == ans.nodes;
    }

    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }


        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info(height, nodes);
    }


    @AllArgsConstructor
    public static class Info {
        int height;
        int nodes;
    }

    public static void main(String[] args) {
        System.out.println(1 << 3);
    }
}
