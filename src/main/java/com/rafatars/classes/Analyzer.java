package com.rafatars.classes;

import lombok.Data;

@Data
public class Analyzer {
    private Graph graph;

    public Analyzer(Graph graph) {
        this.graph = graph;
    }

    public boolean isConnected(Graph graph) {
        Dfs dfs = new Dfs(graph);

        dfs.dfs(graph.getVertices().get(0));

        for (boolean visited : dfs.getVisited()) {
            
            if (!visited) {
                return false;
            }
        }

        return true;
    }

    public boolean isDirected(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.isDirected()) {
                return true;
            }
        }

        return false;
    }

    public boolean isPseudograph(Graph graph) {
        for (Vertex vertex : graph.getVertices()) {
            
            for (int i = 0; i < vertex.getEdges().size(); i++) {
                
                //if the origin and destination are the same, it is a pseudograph
               if(vertex.getEdges().get(i).getOrigin().equals(vertex.getEdges().get(i).getDestination())) {
                   return true;
               }
                
            }
        }

        return false;
    }

    public boolean isReflective(Graph graph) {
        for (Vertex vertex : graph.getVertices()) {
            
            for (int i = 0; i < vertex.getEdges().size(); i++) {
                
                //if at last a vertex dont have a loop it is not reflective
               if(!vertex.getEdges().get(i).getOrigin().equals(vertex.getEdges().get(i).getDestination())) {
                   return false;
               }
                
            }
        }

        return true;
    }

    public boolean isWeighted(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getWeight() != 1) {
                return true;
            }
        }

        return false;
    }

    public boolean isMultigraph(Graph graph) {
        for (Vertex vertex : graph.getVertices()) {
            
            for (int i = 0; i < vertex.getEdges().size(); i++) {
                
                if(vertex.getEdges().get(i).getOrigin().equals(vertex.getEdges().get(i).getDestination())) {
                    return true;
                }
                
            }
        }

        return false;
    }

    public boolean isEmpty(Graph graph) {
        return graph.getEdges().isEmpty();
    }

    public boolean isTrivial(Graph graph) {
        return graph.getVertices().size() == 1;
    }

    public int size(Graph graph) {
        return graph.getEdges().size();
    }

    public int order(Graph graph) {
        return graph.getVertices().size();
    }


}
