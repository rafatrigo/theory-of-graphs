package com.rafatars.classes;

import java.util.ArrayList;

import lombok.Data;

@Data
//Focer-Direct Drawing Algorithm
public class Fdd {
    
    private Graph graph;
    //positions of the vertices
    private ArrayList<Double[]> positions;
    //minimal force in the system
    private double threshold;
    //number of iterations
    private int k;
    //repulsion constant
    private double cRep;
    //attraction constant
    private double cAtt;
    //the ideal length of the edge
    private int length;
    //attraction forces
    private ArrayList<Double[]> attForces;
    //repulsion forces
    private ArrayList<Double[]> repForces;
    //cooling factor
    private double coolingFactor;

    public Fdd(Graph graph, double threshold, int k) {
        this.graph = graph;
        this.threshold = threshold;
        this.k = k;
        this.positions = new ArrayList<>();

        this.cRep = 3.5;
        this.cAtt = 0.9;
        this.length = 1;
        this.coolingFactor = 0.7;

        this.attForces = new ArrayList<>();
        this.repForces = new ArrayList<>();

    }
    
    public ArrayList<Double[]> calculate() {
        
        int t = 0;
        double force = 1;

        //generate random positions for the vertices
        randomPositions(graph.getVertices().size(), 30, 30);

        while(t < this.k && this.threshold < force) {
            //passtrough all vertices
            //calculate the forces
            for (int i = 0; i < graph.getVertices().size(); i++) {
                
                //passtrough all vertices
                for (int j = 0; j < graph.getVertices().size(); j++) {
                    
                    //only do the logic if the vertices are different
                    //because it dont need to calculate the force of a vertex with itself
                    if (i != j) {

                        //calcule the repulsion force between the vertices
                        //and add to the repulsion forces list
                        repForces.add(repulsion(positions.get(i), positions.get(j)));

                    }
                }


                //only has attraction force if the vertices are connected
                Vertex neighbours[] = graph.getVertices().get(i).getNeighbours();

                for (Vertex neighbour : neighbours) {
                    
                    //calcule the attraction force between the vertices
                    //and add to the attraction forces list
                    attForces.add(attraction(positions.get(i), positions.get(neighbour.getNumber())));
                }
                
            }
            
            //apply the forces to the vertices
            for (int i = 0; i < graph.getVertices().size(); i++) {

                Double[] vertexPosition = positions.get(i);
                
                vertexPosition[0] += (repForces.get(i)[0] + attForces.get(i)[0]) * this.coolingFactor;
                vertexPosition[1] += (repForces.get(i)[1] + attForces.get(i)[1]) * this.coolingFactor;
                positions.set(i, vertexPosition);
            }
            
            t++;

            //cleat the vectors
            attForces.clear();
            repForces.clear();

        }

        return positions;
    }

    private void randomPositions(int nPositions, int rangeX, int rangeY) {
        
        //clear if not empty
        if(!this.positions.isEmpty()){
            this.positions.clear();
        }
        
        
        for (int i = 0; i < nPositions; i++) {
            Double[] position = new Double[2];

            //generate a random number between 0 and x
            //and round it to 2 decimal places
            position[0] = (Double)Util.round(Math.random() * rangeX, 2);
            //generate a random number between 0 and y
            //and round it to 2 decimal places
            position[1] = (Double)Util.round(Math.random() * rangeY, 2);

            //add the position to the positions ArrayList
            this.positions.add(position);

        }
        
    }

    /*
     * Calculate the repulsion force between two vertices
     * 
     * Frep = (cRep / distance^2) * direction
     */
    private Double[] repulsion(Double[] vertex, Double[] vertex2) {

        Double[] force = new Double[2];

        //calculate dx
        force[0] = (this.cRep / Math.pow(distanceBettwenVertices(vertex, vertex2), 2)) * this.direction(vertex2, vertex)[0];
        //calculate dy
        force[1] = (this.cRep / Math.pow(distanceBettwenVertices(vertex, vertex2), 2)) * this.direction(vertex2, vertex)[1];


        //round the values
        force[0] = (Double)Util.round(force[0], 2);
        force[1] = (Double)Util.round(force[1], 2);
        
        return force;
    }

    /*
     * Calculate the attraction force between two vertices
     * 
     * Fatt = cAtt * log(distance / length) * direction
     */
    private Double[] attraction(Double[] vertex1, Double[] vertex2) {

        Double[] force = new Double[2];

        //calculate dx
        force[0] = cAtt * Math.log(distanceBettwenVertices(vertex1, vertex2) / this.length) * this.direction(vertex1, vertex2)[0];
        //calculate dy
        force[1] = cAtt * Math.log(distanceBettwenVertices(vertex1, vertex2) / this.length) * this.direction(vertex1, vertex2)[1];
        
        //round the values
        force[0] = (Double)Util.round(force[0], 2);
        force[1] = (Double)Util.round(force[1], 2);
        
        return force;
    }

    private double distanceBettwenVertices(Double[] vertex, Double[] vertex2) {

        double distance = Math.sqrt(Math.pow(vertex[0] - vertex2[0], 2) + Math.pow(vertex[1] - vertex2[1], 2));

        //round the value
        distance = Util.round(distance, 2);
        
        //return the absolute value of the distance
        return Math.abs(distance);
    }

    //calculate the vector direction of vector1 to vector2
    private double[] direction(Double[] vertex2, Double[] vertex) {

        double direction[] = new double[2];

        direction[0] = vertex[0] - vertex2[0];
        direction[1] = vertex[1] - vertex2[1];

        //round the values
        direction[0] = Util.round(direction[0], 2);
        direction[1] = Util.round(direction[1], 2);

        return direction;
    }
    
    
}
