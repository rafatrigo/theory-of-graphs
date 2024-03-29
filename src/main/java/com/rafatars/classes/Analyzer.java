package com.rafatars.classes;

import lombok.Data;

@Data
public class Analyzer {
    private Graph graph;

    public Analyzer(Graph graph) {
        this.graph = graph;
    }

    public boolean isDirected(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.isDirected()) {
                return false;
            }
        }

        return true;
    }

    public int size(Graph graph) {
        return graph.getEdges().size();
    }
}
