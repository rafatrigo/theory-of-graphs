package com.rafatars;

import java.io.FileNotFoundException;

import com.rafatars.classes.Graph;

public class Main {
    public static void main(String[] args) {
        
        try {
            Graph graph = new Graph("src/main/resources/graph.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}