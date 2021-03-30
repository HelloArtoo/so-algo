package com.artoo.algo.tree;

/**
 * 中序遍历，求二叉树的后继节点 o(k)非o(n)的复杂度优化
 *
 * 前驱节点类似
 *
 *
 *
 * （前驱：有左找最右，没左找父右
 *   后继：有右找最左，没右找父左）
 */
public class TreeNextNode {

    //找前驱节点
    //1、如果有左树，找左树的最右节点
    //2、否则，当前节点是否是父节点的左节点，是的话继续找，直到找到是右节点


    /**
     * 中序遍历找后继节点：
     * 1、如果有右树，x的后继节点，是右树的最左节点
     * 2、如果没有右树，x的最左节点是向上找第一个左树的节点。
     * 3、最右的节点是没有后继节点，如果一直往上没有找到是这个树做孩子的情况，那这个节点是最右的节点
     *
     * @param node
     * @return
     */
    public static TreeNode findNextNode(TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return findLeftNode(node.right);
        } else {
             return findParentLeftNode(node);
        }
    }

    private static TreeNode findParentLeftNode(TreeNode node) {
        TreeNode ans = null;
        while (node != null) {
            TreeNode parent = node.parent;
            if (parent.left == node) {
                return parent;
            } else {
                node = parent;
            }
        }

        //如果一直没找到，则代表当前是最右节点，直接返回null
        return ans;
    }

    private static TreeNode findLeftNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
