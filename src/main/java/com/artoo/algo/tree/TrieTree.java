package com.artoo.algo.tree;

import java.util.HashMap;

/**
 * 前缀树，字典树
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 */
public class TrieTree {

    public static class TrieNode1 {
        private int pass;
        private int end;
        private TrieNode1[] next;

        TrieNode1() {
            pass = 0;
            end = 0;
            next = new TrieNode1[26];//26个字母
        }
    }

    public static class Trie1 {

        private TrieNode1 root;

        Trie1() {
            root = new TrieNode1();
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }

            TrieNode1 node = root;
            node.pass++;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TrieNode1();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            //先搜索
            if (search(word) != 0) {
                TrieNode1 node = root;
                node.pass--;
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    int index = arr[i] - 'a';
                    if(--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        //word这个单词之前加入过几次
        public int search(String word) {
            if (word == null || word.length() < 1) {
                return 0;
            }

            TrieNode1 node = root;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }

            return node.end;
        }

        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null || pre.length() < 1) {
                return 0;
            }
            TrieNode1 node = root;
            char[] arr = pre.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }

            return node.pass;
        }

    }


    public static void main(String[] args) {

    }

    public static class TrieNode2 {
        private int pass;
        private int end;
        private HashMap<Integer, TrieNode2> next;

        TrieNode2() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class Trie2 {

        private TrieNode2 root;

        Trie2() {
            root = new TrieNode2();
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }

        }

        public void delete(String word) {
        }

        //word这个单词之前加入过几次
        public int search(String word) {
            if (word == null || word.length() < 1) {
                return 0;
            }
            return 0;
        }

        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null || pre.length() < 1) {
                return 0;
            }
            return 0;
        }

    }
}
