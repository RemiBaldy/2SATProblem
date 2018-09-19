package com.company;

public class GraphTransposer implements Graph.ArcConsumer {

    @Override
    public void apply(int source,int dest,Object o){
            System.out.println("  source="+source+"  dest="+dest+"  label="+o);

    }

/*
    public  transposeGraph(Graph graph){

            }*/
}
