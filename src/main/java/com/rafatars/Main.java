package com.rafatars;

import com.rafatars.classes.Graph;

public class Main {
    public static void main(String[] args) {
        
        String graphPath = "src/main/resources/graph.txt";

        Graph graph = new Graph(graphPath);        
        
        graph.analyze();
        graph.showAnalyze();
    }
}