package com.rafatars.classes;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Vertex {
    private ArrayList<Edge> edges;
    private int number;

    public Vertex(int number, ArrayList<Edge> edges) {
        this.number = number;
        this.edges = edges;
    }

    public Vertex(int number) {
        this.number = number;
        this.edges = new ArrayList<>();
    }

    public Vertex[] getNeighbours(){
        Vertex[] neighbours = new Vertex[this.edges.size()];

        for(int i = 0; i < this.edges.size(); i++){
            
            if(!this.edges.get(i).getOrigin().equals(this)){
                neighbours[i] = this.edges.get(i).getOrigin();
            }else{
                neighbours[i] = this.edges.get(i).getDestination();
            }
            
        }

        return neighbours;
    }
}
