package com.artoo.algo.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * 1）可以用先序或者中序或者后序或者按层遍历，来实现二叉树的序列化
 * 2）用了什么方式序列化，就用什么样的方式反序列化
 */
public class SerializableTree {


    //序列化的时候不要忽略空节点，把整个树补全 [1,1,null,1,null,null,null]
    public static TreeNode buildByPreQueue(Queue<Integer> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }

        return preBuild(prelist);
    }

    //先序方式还原，后序中序类似
    private static TreeNode preBuild(Queue<Integer> prelist) {
        Integer nodeVal = prelist.poll();
        if (nodeVal == null) {
            return null;
        }

        TreeNode node = new TreeNode(nodeVal);
        node.left = preBuild(prelist);
        node.right = preBuild(prelist);
        return node;
    }


    //#################   BFS的序列话和反序列话
    public static TreeNode buildBfsNode(Queue<Integer> bfsList) {
        if (bfsList == null || bfsList.size() == 0) {
            return null;
        }

        TreeNode head = makeNode(bfsList.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head.left = makeNode(bfsList.poll());
            head.right = makeNode(bfsList.poll());

            if (head.left != null) {
                queue.offer(head.left);
            }

            if (head.right != null) {
                queue.offer(head.right);
            }
        }

        return head;
    }

    private static TreeNode makeNode(Integer poll) {
        if (poll == null) {
            return null;
        }
        return new TreeNode(poll);
    }
}
