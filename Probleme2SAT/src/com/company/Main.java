package com.company;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args) {


       ConverterTextToGraph txtGraphConverter = new ConverterTextToGraph("formula-modified.txt");
        try {
            txtGraphConverter.readTextGraphFile();
        }
        catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }

        try {
            Graph graph = txtGraphConverter.initializeGraph();

            /*GraphPrinter graphPrinter = new GraphPrinter();
            graphPrinter.showGraph(graph);*/
            GraphTransposer tr = new GraphTransposer();
            graph.iterEdges(tr);

            System.out.println(graph.toString());
        }
        catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }





    }



}
