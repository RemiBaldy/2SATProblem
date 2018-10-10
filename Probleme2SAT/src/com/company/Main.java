package com.company;


import java.io.IOException;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args) {


       TextToGraphConverter txtGraphConverter = new TextToGraphConverter("testclassi.txt");

        try {
            Graph<String> graph = txtGraphConverter.initializeGraph();
            System.out.println(graph);

            GraphTranspose graphTransposer = new GraphTranspose(graph.order());
            graph.iterEdges(graphTransposer);
            Graph transposedGraph = graphTransposer.getTransposedGraph();
            System.out.println(transposedGraph);

            DepthFirstSearcher dFS = new DepthFirstSearcher(graph);
            System.out.println("Parcours en profondeur du graphe");
            dFS.Search();
            dFS.setGraph(transposedGraph);
            System.out.println("Parcours en profondeur du graphe transpose par date de fin\n");
            dFS.SearchByEndingDate();
            System.out.println("Composante fortement connexes\n");
            dFS.printStronglyConnectedComponents();
            System.out.println("Composante fortement connexes dont l'index des sommets est convertie en variable x(i)");
            dFS.convertStronglyConnectedComponentsVertexIndexToFormulaVariable();
            dFS.printStronglyConnectedComponents();

            if(dFS.isFormulaSatisfiable())
                System.out.println("Formule Satisfaisable !\n");
            else
                System.out.println("Formule Antilogique :(\n");

            System.out.println("Combinaisons de valeurs satisfaisant la formule :");
            dFS.printValuesSatisfyingFormula();
        }
        catch (IOException | InputMismatchException ex) {
            ex.printStackTrace();
        }


    }



}
