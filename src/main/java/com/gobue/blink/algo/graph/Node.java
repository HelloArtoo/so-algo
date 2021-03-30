package com.gobue.blink.algo.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点
 */
@Data
public class Node {
    private int value;
    //出度（指向该点的边）
    private int in;
    //入度（该点指向的边）
    private int out;
    //指向的点集合
    private List<Node> nexts;
    //指向的边集合
    private List<Edge> edges;

    public Node(int value) {
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void incrOut() {
        this.out++;
    }

    public void decrOut() {
        this.out--;
    }

    public void incrIn() {
        this.in++;
    }

    public void decrIn() {
        this.in--;
    }
}
