package com.artoo.algo.tree;

import lombok.AllArgsConstructor;

/**
 * 给定一颗二叉树头节点head， 和另外两个节点a、b返回a和b的最低公共祖先
 */
public class LowestParent {

    //非递归，用一个hashmap记录所有的父节点，然后找a一条线，放进set，然后去找b，第一个在set中的数就是公共父节点


    //递归方式：
    // 1、o1o2没有一个在x为头的树上
    // 2、 o1o2只有一个在x为头的树上
    // 3、o1o2都在x为头的树上
    //          a、
    public static Info findLowestParent(TreeNode x, TreeNode o1, TreeNode o2) {
        if (x == null) {
            return new Info(null, false, false);
        }

        Info left = findLowestParent(x.left, o1, o2);
        Info right = findLowestParent(x.right, o1, o2);

        boolean findO1 = (x == o1 || left.findO1 || right.findO1);
        boolean findO2 = (x == o2 || left.findO2 || right.findO2);
        TreeNode ans = null;//交汇点
        if (left.ans != null) {
            ans = left.ans;
        } else if (right.ans != null) {
            ans = right.ans;
        } else if (findO1 && findO2) {
            ans = x;
        }
        return new Info(ans, findO1, findO2);
    }

    @AllArgsConstructor
    public static class Info {
        TreeNode ans;//最初的交汇点
        boolean findO1;//是否有O1
        boolean findO2;//是否有O2
    }

}
