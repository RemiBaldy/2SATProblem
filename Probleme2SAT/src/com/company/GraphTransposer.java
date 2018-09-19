package com.company;

public class GraphTransposer implements Graph.ArcConsumer {

    Graph transposedGraph;

    GraphTransposer(int size){
        transposedGraph = new Graph(size);
    }

    @Override
    public void apply(int source,int dest,Object o){
        source = 2;
    }

}
