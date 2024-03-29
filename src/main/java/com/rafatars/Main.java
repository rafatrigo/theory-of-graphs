package com.rafatars;

import com.rafatars.classes.Analyzer;
import com.rafatars.classes.Graph;

public class Main {
    public static void main(String[] args) {
        
        String graphPath = "src/main/resources/graph.txt";

        Graph graph = new Graph(graphPath);
        
        Analyzer analyzer = new Analyzer(graph);

        System.out.println("O grafo é simples: " + !analyzer.isDirected(graph));
        System.out.println("Ordem do grafo: " + analyzer.order(graph));
        System.out.println("O tamanho do grafo é: " + analyzer.size(graph));
        System.out.println("É conexo: " + analyzer.isConnected(graph));
        System.out.println("É um pseudografo: " + analyzer.isPseudograph(graph));
        System.out.println("É reflexivo: " + analyzer.isReflective(graph));
        System.out.println("É ponderado: " + analyzer.isWeighted(graph));
        System.out.println("É multigrafo: " + analyzer.isMultigraph(graph));
        System.out.println("É vazio: " + analyzer.isEmpty(graph));
        System.out.println("É trivial: " + analyzer.isTrivial(graph));




        
    }
}