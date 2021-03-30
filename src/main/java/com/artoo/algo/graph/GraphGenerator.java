package com.artoo.algo.graph;


public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    public static Graph createGraph(Integer[][] matrix) {

        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            if (!graph.getNodes().containsKey(from)) {
                graph.getNodes().put(from, new Node(from));
            }

            if (!graph.getNodes().containsKey(to)) {
                graph.getNodes().put(to, new Node(to));
            }


            Node fromNode = graph.getNodes().get(from);
            Node toNode = graph.getNodes().get(to);
            fromNode.incrOut();
            toNode.incrIn();
            fromNode.getNexts().add(toNode);

            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.getEdges().add(edge);
            graph.getEdges().add(edge);
        }
        return graph;
    }
}
