package com.company;

public class GraphTranspose implements Graph.ArcConsumer {

    Graph transposedGraph;

    GraphTranspose(int size){
        transposedGraph = new Graph(size);
    }

    @Override
    public void apply(int source,int dest,Object o){
        //System.out.println("source :"+ dest + "  dest :"+ source);
        transposedGraph.addArc(dest, source, "transposedGraph");
    }

    public String toString() {return transposedGraph.toString();}

    public Graph returnGraphMember(){
        return transposedGraph;
    }

}
