package com.company;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public class DepthFirstSearcher {

    private Graph graph;
    private int ancestor;
    private int beginningProcessDate;
    private int endingProcessDate;
    private ArrayList nodeColor;

    public DepthFirstSearcher(Graph graph) {
        ancestor = 0;
        beginningProcessDate = 0;
        endingProcessDate = 0;
        this.graph = graph;
        this.nodeColor = new ArrayList();
        for(int i=0; i < graph.order(); i++)
            nodeColor.add(i, "blanc");
    }

    public void DepthFirstSearch(){
        //graph.iterEdges(new DepthSearchConsumer());
    }

    public void printColorArray(){
        System.out.println("size : "+nodeColor.size());
        ListIterator list = nodeColor.listIterator();
        while(list.hasNext())
            System.out.println(list.next());
    }

    public ArrayList<Integer> getNodeColor() {
        return nodeColor;
    }
/*
    private class DepthSearchConsumer implements Graph.ArcConsumer{
        @Override
        public void apply(int source, int dest, Object o) {
            if(nodeColor.get(source) == "blanc")
                DepthFirstSearcher()

        }
    }*/

}
