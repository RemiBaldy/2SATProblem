package com.company;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextToGraphConverter {


    public File file;
    public Scanner scanner;


    TextToGraphConverter(String filePath){
        file = new File(filePath);
    }


    public void readTextGraphFile() throws IOException,InputMismatchException{
        scanner = new Scanner(file);

        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
        scanner.close();
    }

    public int[] modifyEdgeInputsToInsertInGraphArray(int source, int destination, int sizeGraph){
        if(source < 0)
            source += sizeGraph;
        else
            source -= 1;

        if (destination < 0)
            destination += sizeGraph;
        else
            destination -= 1;

        return new int[] {source, destination};
    }


    public Graph<String> initializeGraph() throws IOException,InputMismatchException{
        scanner = new Scanner(file);

        if(scanner.hasNextInt()) {
            Graph<String> graph = new Graph<String>(scanner.nextInt()*2);

            while(scanner.hasNextInt()){
                int source = scanner.nextInt();
                if(scanner.hasNextInt()){
                    int destination = scanner.nextInt();
                    int[] normalizedInputs= modifyEdgeInputsToInsertInGraphArray(source, destination, graph.order());
                    graph.addArc(normalizedInputs[0], normalizedInputs[1], "graph");
                }
            }
            scanner.close();
            return graph;
        }
        scanner.close();
        return new Graph<>(0);
    }




}
