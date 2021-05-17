package com.artoo.algo.graph;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 * <p>
 * 两个核心api o(1)的复杂度
 * <p>
 * 1、boolean isSameSet(V x, V y); 查询样本x和样本y是否属于一个集合
 * 2、void union(V x, V y); 把x和y各自所在集合的所有样本合并成一个集合
 */
public class UnionFind {

    public static class Node<V> {
        private V value;

        public Node() {
        }

        public Node(V value) {
            this.value = value;
        }
    }

    /**
     * 1、组成新node, 然后进行原始指向 V -> Node<V>
     * 2、寻找根，findFather，如果根一样，则进行挂载
      */
    public static class UnionSet<V> {

        private Map<V, Node<V>> nodes;
        private Map<Node<V>, Node<V>> parents;
        private Map<Node<V>, Integer> sizeMap;


        public UnionSet() {
        }

        public UnionSet(List<V> values) {

            for (V v : values) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }

        }

        private Node<V> findFather(V x) {
            Node<V> cur = nodes.get(x);
            Stack<Node> stack = new Stack<>();
            while (parents.get(cur) != null) {
                stack.push(cur);
                cur = parents.get(cur);
            }

            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(V x, V y) {
            if (nodes.get(x) == null || nodes.get(y) == null) {
                return false;
            }

            return findFather(x) == findFather(y);
        }

        //小挂大
        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return;
            }

            Node<V> xnodes = findFather(x);
            Node<V> ynodes = findFather(y);

            if (xnodes == ynodes) {
                return;
            }

            int xsize = sizeMap.get(xnodes);
            int ysize = sizeMap.get(ynodes);

            Node<V> small = null;
            Node<V> big = null;

            if (xsize > ysize) {
                big = xnodes;
                small = ynodes;
            } else {
                big = ynodes;
                small = xnodes;
            }

            parents.put(small, big); //小挂大
            sizeMap.put(big, xsize + ysize);
            sizeMap.remove(small);
        }
    }
}
