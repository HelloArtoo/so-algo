package com.artoo.algo.graph;

import java.util.*;

/**
 * 图拓扑排序
 */
public class TopologySort {

    /**
     * 1）在图中找到所有入度为0的点输出
     * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
     * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
     */
    public List<Node> tsort(Graph graph) {

        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : graph.getNodes().values()) {
            inMap.put(node, node.getIn());
            //入度为0
            if (node.getIn() == 0) {
                queue.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            result.add(poll);
            for (Node next : poll.getNexts()) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }

        return result;
    }
}
