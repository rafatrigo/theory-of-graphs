package com.rafatars;

import com.rafatars.classes.Analyzer;
import com.rafatars.classes.Graph;

public class Main {
    public static void main(String[] args) {
        
        String graphPath = "src/main/resources/graph.txt";

        Graph graph = new Graph(graphPath);
        
        Analyzer analyzer = new Analyzer(graph);

        System.out.println("O grafo é simples: " + analyzer.isDirected(graph));
        System.out.println("O tamanho do grafo é: " + analyzer.size(graph));
        
    }
}