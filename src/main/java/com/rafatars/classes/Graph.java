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
                    if(value != "0"){

                        //get the vertex that corresponds to the actual column
                        Vertex vertex = vertices.get(i);

                        if(value == "-1" || edge.getOrigin() != null){
                            
                            if(value.equals("-1")){
                                edge.setDirected(true);
                            }

                            //set the vertex in the edge destination
                            edge.setDestination(vertex);

                        }else{
                            //set the vertex in the edge origin
                            edge.setOrigin(vertex);
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
}
