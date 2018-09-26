package com.company;


import java.io.IOException;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args) {


       ConverterTextToGraph txtGraphConverter = new ConverterTextToGraph("formula-modified.txt");

        try {
            Graph<String> graph = txtGraphConverter.initializeGraph();
            System.out.println(graph.toString());

            GraphTranspose graphTransposer = new GraphTranspose(graph.order());
            graph.iterEdges(graphTransposer);
            Graph transposedGraph = graphTransposer.returnGraphMember();

            System.out.println(transposedGraph.toString());

            DepthFirstSearcher dFS = new DepthFirstSearcher(graph);
            dFS.Search();
            dFS.printDepthFirstSearchInformations();
        }
        catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }






    }



}
