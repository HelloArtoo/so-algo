package com.artoo.algo.tree;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是不是完全二叉树
 *
 * 思路：
 * 1、任何节点有右无左，return false
 * 2、否则当遇到左右不双全的节点的时候，以后遇到的所有节点必须要是叶节点。
 */
public class IsCBT {

    //方式1：非递归方式
    //          1、用BFS判断
    //          2、任何节点有右无左则false，否则继续
    //          3、一旦遇到左右孩子不双全的时候，后序所有的节点必须是叶子节点，否则返回false
    public static boolean isCBT(TreeNode head) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        boolean flag = false; //是否到了叶子节点临界点
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            //条件二和三
            if ((flag && (left != null && right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }

            if (left == null || right == null) {
                flag = true;
            }
        }
        return true;
    }


    //方式2：递归方式
    //      1、是个满二叉树，无缺口
    //      2、左树是完全二叉树，右树是满二叉树，且左树高度比右树多1
    //      3、左树满二叉树，右树满二叉树，左树高度比右树多1
    //      4、左树是满的，右树是完全，且高度一致
    public static Info isCBT2(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info left = isCBT2(head.left);
        Info right = isCBT2(head.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isFBT = left.isFBT && right.isFBT && left.height == right.height;
        boolean isCBT = false;
        if (isFBT) {
            isCBT = true;
        } else if (left.isCBT && right.isCBT) {
            if (left.isCBT && right.isFBT && left.height == (right.height + 1)) {
                isCBT = true;
            } else if (left.isFBT && right.isFBT && left.height == (right.height + 1)) {
                isCBT = true;
            } else if (left.isFBT && right.isCBT && left.height == right.height) {
                isCBT = true;
            }
        }

        return new Info(isCBT, isFBT, height);
    }

    @AllArgsConstructor
    public static class Info {

        private boolean isCBT;//完全二叉树
        private boolean isFBT;//满二叉树
        private int height;
    }


}
