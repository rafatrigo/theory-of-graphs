package com.rafatars.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lombok.Data;

@Data
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    private boolean simple;
    private boolean directed;
    private boolean complete;
    private int order;
    private int size;
    private boolean connected;
    private boolean pseudograph;
    private boolean reflective;
    private boolean weighted;
    private boolean multigraph;
    private boolean empty;
    private boolean trivial;


    public Graph(String filePath) {

        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        
        File file = new File(filePath);
        Scanner scan;
        try {
            scan = new Scanner(file);

            boolean startVertices = false;
            //scan until the file has no more lines
            while (scan.hasNextLine()) {
                //get the line
                String line = scan.nextLine();

                //transform the line into a char array
                String[] lineCharacters = line.split(",");

                //create the vertices
                if(!startVertices){
                    for(int i = 0; i < lineCharacters.length; i++){

                        vertices.add(new Vertex(i));
                    }
                    startVertices = true;
                }

                //create edge
                Edge edge = new Edge();

                //passtrough each character of the line
                for(int i = 0; i < lineCharacters.length; i++){
                    
                    String value = lineCharacters[i];
                    if(!value.equals("0")){

                        //get the vertex that corresponds to the actual column
                        Vertex vertex = vertices.get(i);

                        if(value.equals("-1") || edge.getOrigin() != null || edge.getOrigin() != null && i == lineCharacters.length - 1){
                            
                            if(value.equals("-1")){
                                edge.setDirected(true);
                            }

                            //set the vertex in the edge destination
                            edge.setDestination(vertex);
                            //add the edge to the vertex edges list
                            vertex.getEdges().add(edge);

                        }else{
                            //set the vertex in the edge origin
                            edge.setOrigin(vertex);
                            //add the edge to the vertex edges list
                            vertex.getEdges().add(edge);
                        }
                    }
                }

                //add the edge to the edges list
                edges.add(edge);

            }

            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("Path not found!\nVerify the path and try again.\nPath: " + filePath);
        }
    }

    public void analyze() {
        Analyzer analyzer = new Analyzer(this);

        this.directed = analyzer.isDirected(this);
        this.complete = analyzer.isComplete(this);
        this.order =  analyzer.order(this);
        this.size =  analyzer.size(this);
        this.connected = analyzer.isConnected(this);
        this.pseudograph =  analyzer.isPseudograph(this);
        this.reflective =  analyzer.isReflective(this);
        this.weighted = analyzer.isWeighted(this);
        this.multigraph = analyzer.isMultigraph(this);
        this.empty = analyzer.isEmpty(this);
        this.trivial = analyzer.isTrivial(this);

        if(
            !this.directed && 
            !this.pseudograph && 
            !this.reflective && 
            !this.weighted && 
            !this.multigraph && 
            !this.empty && 
            !this.trivial)
            {
            this.simple = true;
            }
    }

    public void showAnalyze(){

        System.out.println("\n-------Análise do grafo------------\n");
        
        System.out.println("O grafo é simples: " + this.simple);
        System.out.println("O grafo é direcionado: " + this.directed);
        System.out.println("O grafo é completo: " + this.complete);
        System.out.println("Ordem do grafo: " + this.order);
        System.out.println("O tamanho do grafo é: " + this.size);
        System.out.println("É conexo: " + this.connected);
        System.out.println("É um pseudografo: " + this.pseudograph);
        System.out.println("É reflexivo: " + this.reflective);
        System.out.println("É ponderado: " + this.weighted);
        System.out.println("É multigrafo: " + this.multigraph);
        System.out.println("É vazio: " + this.empty);
        System.out.println("É trivial: " + this.trivial);
        
    }
}
