package com.rafatars.classes;

import lombok.Data;

//Depth First Search Algorithm
@Data
public class Dfs {
    private Graph graph;
    private boolean visited[];

    public Dfs(Graph graph) {
        this.graph = graph;

        //initialize the visited array with the size of the number of vertices
        //and set all values to false
        this.visited = new boolean[graph.getVertices().size()];
    }

    public void dfs(Vertex vertex) {
        
        if(visited[vertex.getNumber()]) {
            return;
        }

        visited[vertex.getNumber()] = true;

        Vertex[] neighbours = vertex.getNeighbours();

        for(Vertex neighbour : neighbours) {
            dfs(neighbour);
        }
    }

}
