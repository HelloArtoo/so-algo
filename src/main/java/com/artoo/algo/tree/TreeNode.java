package com.gobue.blink.algo.tree;

import java.util.Stack;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        System.out.println("前序：");
        preTraversal(this);
        System.out.println();

        System.out.println("中序：");
        midTraversal(this);
        System.out.println();

        //后序遍历
        System.out.println("后序：");
        postTraversal(this);
        System.out.println();
        return "";
    }


    private void preTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.print(treeNode.val + " ");
        preTraversal(treeNode.left);
        preTraversal(treeNode.right);
    }

    //非递归：
    //压栈方式：1、弹出先打印，2、如有右，则压右，3、如有左，则压入左
    //先压入右再压入左
    private void preTraversal2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    private void midTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        midTraversal(treeNode.left);
        System.out.print(treeNode.val + " ");
        midTraversal(treeNode.right);
    }

    //中序遍历：
    //1、整条左边界入栈
    //2、弹出就打印，弹出的节点的整条左边界压入栈
    private void midTraversal2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || treeNode != null) {
            if (treeNode == null) {
                TreeNode node = stack.pop();
                System.out.println(node.val);
                treeNode = treeNode.right;
            } else {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
        }

    }

    //后序的非递归，通过先序头左右，变成头右左，然后逆序输出（通过两个栈的方式）
    private void postTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        postTraversal(treeNode.left);
        postTraversal(treeNode.right);
        System.out.print(treeNode.val + " ");
    }

    //后序的非递归，通过先序头左右，变成头右左，然后逆序输出（通过两个栈的方式）
    private void postTraversal2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            stack2.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }


    //后序的非递归，一个栈的方式
    private void postTraversal3(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode currentNode = null, lastDone = treeNode;
        while (!stack.isEmpty()) {
            currentNode = stack.peek();
            //1、当左树没处理的情况下，处理左树。
            if (currentNode.left != null && currentNode != lastDone.left && currentNode != lastDone.right) {
                stack.push(currentNode.left);
            } else if (currentNode.right != null && currentNode != lastDone.right) {  //2、当右树没处理的情况下，处理右树。
                stack.push(currentNode.right);
            } else { //3、左右都处理完了，打印。
                System.out.println(stack.pop().val);
                lastDone = currentNode;
            }
        }
    }
}
