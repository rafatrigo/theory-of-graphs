package com.rafatars;

import com.rafatars.classes.ArrayToJson;
import com.rafatars.classes.Graph;
import com.rafatars.classes.Util;
import com.rafatars.classes.Fdd;

import com.*;

public class Main {
    public static void main(String[] args) {
        
        String graphPath = "src/main/resources/v4Conected.txt";

        Graph graph = new Graph(graphPath);        
        
        graph.analyze();

        Fdd fdd = new Fdd(graph, 0, 50);

        fdd.calculate();

        ArrayToJson.generateJson(fdd.getPositions(), graph.getEdges(), "src/main/resources/", "jsonGraph");
    }
}