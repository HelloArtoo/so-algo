package com.artoo.algo.tree;


import lombok.AllArgsConstructor;
import lombok.Data;

public class BigestBSTHead {


    /**
     * 给定一棵二叉树的头节点head，
     * 返回这颗二叉树中最大(节点个数最多)的二叉搜索子树的大小
     */
    public static Info biggestBSTNodes(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info left = biggestBSTNodes(head.left);
        Info right = biggestBSTNodes (head.right);
        int min = head.val;
        int max = head.val;
        int maxSubBSTSize = 0;
        boolean isAllBST = false;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            maxSubBSTSize = Math.max(maxSubBSTSize, left.maxSubBSTSize);
        }

        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTSize);
        }


        //第三步，左右皆平衡，加上当前也是平衡(当前大于左边的最大，小于右边的最小)
        if ((left == null ? true : (left.isAllBST && head.val > left.max))
                && (right == null ? true : (right.isAllBST && head.val < right.min))) {
            isAllBST = true;
            maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize) + (right == null ? 0 : right.maxSubBSTSize) + 1;
        }


        return new Info(isAllBST, maxSubBSTSize, min, max);
    }


    @Data
    @AllArgsConstructor
    public static class Info {
        //是否都是BST
        boolean isAllBST;
        //最大节点个数
        int maxSubBSTSize;
        //最小值
        int min;
        //最大值
        int max;
    }
}
