package com.artoo.algo.string;

import java.util.ArrayList;

/**
 * KMP搞定：
 * <p>
 * 两棵树，判断一棵树是否是另一个棵数的子树
 * <p>
 * 1、先序序列化
 * 2、然后判断是否包含子字串
 * <p>
 * null
 */
public class TreeEqual {


    public static void main(String[] args) {

    }

    //------------------------------------------------------------------
    public static boolean containsTree1(Node big, Node small) {

        if (small == null) {
            return true;
        }

        if (big == null) {
            return false;
        }

        //两个树一模一样
        if (isSameStructure(big, small)) {
            return true;
        }

        return containsTree1(big.left, small) || containsTree1(big.right, small);

    }

    private static boolean isSameStructure(Node node1, Node node2) {
        if (node1 == null && node2 != null) {
            return false;
        }

        if (node1 != null && node2 == null) {
            return false;
        }

        if (node1 == null || node2 == null) {
            return true;
        }

        if (node1.value != node2.value) {
            return false;
        }

        return isSameStructure(node1.left, node2.left) && isSameStructure(node1.right, node2.right);
    }
    //------------------------------------------------------------------

    //KMP方式
    public static boolean containsTree2(Node big, Node small) {

        if (small == null) {
            return true;
        }

        if (big == null) {
            return false;
        }

        ArrayList<String> b = preOrder(big);
        ArrayList<String> s = preOrder(small);
        String[] bs = b.toArray(new String[]{});
        String[] ss = s.toArray(new String[]{});
        return getIndexOf(bs, ss) != -1;
    }

    private static int getIndexOf(String[] str1, String[] str2) {
        if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
            return -1;
        }
        int x = 0, y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (isEqual(str1[x], str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(String[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int x = 2;
        while (x < next.length) {
            if (isEqual(ms[x - 1], ms[cn])) {
                next[x++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[x++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    private static ArrayList<String> preOrder(Node big) {
        ArrayList<String> lst = new ArrayList<>();
        processPreOrder(big, lst);
        return lst;
    }

    private static void processPreOrder(Node node, ArrayList<String> lst) {
        if (node == null) {
            lst.add(null);
            return;
        }

        lst.add(String.valueOf(node.value));
        processPreOrder(node.left, lst);
        processPreOrder(node.right, lst);
    }
    //------------------------------------------------------------------


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


}
