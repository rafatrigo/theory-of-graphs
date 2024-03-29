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
}
