package com.rafatars.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Data;

@Data
public class ArrayToJson {
    
    public static void generateJson(ArrayList<Double[]> positionsUnformated, ArrayList<Edge> edges, String path, String fileName) {
       
        ArrayList<Double[]> positions = Util.roundBigArray(positionsUnformated, 2);
        
        try {
            FileWriter writer = new FileWriter(path + fileName + ".json");

            //beginning of the json file
            writer.write("{\n");

            //beginning of the vertices
            writer.write("\t\"vertices\": [\n");
            
            for(int i = 0; i< positions.size(); i++) {

                writer.write("\t\t{\n");
                writer.write("\t\t\t\"x\": " + positions.get(i)[0] + ",\n");
                writer.write("\t\t\t\"y\": " + positions.get(i)[1] + "\n");
                writer.write("\t\t}");

                if(i != positions.size() - 1) {
                    writer.write(",\n");
                }else {
                    writer.write("\n");
                }
                
            }
            
            //end of the vertices
            writer.write("\t],\n");

            //beginning of the edges
            writer.write("\t\"edges\": [\n");

            
            for(int i = 0; i< edges.size(); i++) {
                
                writer.write("\t\t{\n");
                writer.write("\t\t\t\"origin\": " + edges.get(i).getOrigin().getNumber() + ",\n");
                writer.write("\t\t\t\"destination\": " + edges.get(i).getDestination().getNumber() + ",\n");
                writer.write("\t\t\t\"directed\": " + edges.get(i).isDirected() + "\n");
                writer.write("\t\t}");

                if(i != edges.size() - 1) {
                    writer.write(",\n");
                }else {
                    writer.write("\n");
                }
                
            }

            //end of the edges
            writer.write("\t]\n");

            //end of the json file
            writer.write("}\n");
            
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred on the FileWriter.");
        }
        
    }
    
}
