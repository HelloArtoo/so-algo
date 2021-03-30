package com.artoo.algo.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 边
 */
@Data
@AllArgsConstructor
public class Edge {
    private int weight;
    private Node from;
    private Node to;
}
