package com.company;

import java.util.List;

public class GraphPrinter implements Graph.ArcConsumer {

    @Override
    public void apply(int source, int dest, Object o) {
        System.out.println("  source="+source+"  dest="+ dest+"  label="+o);
    }
/*
    public void showGraph(Graph graph) {
        //GraphPrinter it = new GraphPrinter();
        graph.iterEdges(this);
    }
*/
}
