package com.company;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConverterTextToGraph {


    public File file;
    public Scanner scanner;


    ConverterTextToGraph(String filePath){
        file = new File(filePath);
    }


    public void readTextGraphFile() throws IOException,InputMismatchException{
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
    }


    public Graph initializeGraph() throws IOException,InputMismatchException{
        Scanner scanner = new Scanner(file);

        if(scanner.hasNextInt()) {
            Graph graph = new Graph(scanner.nextInt());

            while(scanner.hasNextInt()){
                int source = scanner.nextInt();
                if(scanner.hasNextInt()){
                    int destination = scanner.nextInt();
                    graph.addArc(source, destination, "test");
                }
            }
            return graph;
        }
        return null;
    }




}
