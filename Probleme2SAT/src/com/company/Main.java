package com.company;


import java.io.IOException;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args) {


       TextToGraphConverter txtGraphConverter = new TextToGraphConverter("test.txt");

        try {
            Graph<String> graph = txtGraphConverter.initializeGraph();
            System.out.println(graph.toString());

            GraphTranspose graphTransposer = new GraphTranspose(graph.order());
            graph.iterEdges(graphTransposer);
            Graph transposedGraph = graphTransposer.getTransposedGraph();

            System.out.println(transposedGraph);

            DepthFirstSearcher dFS = new DepthFirstSearcher(graph);
            dFS.Search();
            dFS.setGraph(transposedGraph);
            dFS.SearchByEndingDate();
            dFS.printStronglyConnectedComponents();
            dFS.convertStronglyConnectedComponentsVertexIndexToFormulaVariable();
            dFS.printStronglyConnectedComponents();            
            if(dFS.isFormulaSatisfiable())
                System.out.println("Formule Satisfaisable");
            else
                System.out.println("Formule Antilogique");
        }
        catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }


    }



}
