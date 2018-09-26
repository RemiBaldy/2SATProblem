package com.company;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public class DepthFirstSearcher {

    private Graph graph;
    private int date;
    private ArrayList<VertexInformations> DepthFirstSearchInformations;
    
    private class VertexInformations{
    	public String color;
    	public int beginningProcessDate;
    	public int endingProcessDate;
    	public int ancestor;
    	
    	public VertexInformations(){
    		color = "blanc";    		
    	}
    	public String toString() {
    		return "color : "+color+"  Ancetre : "+ancestor+"  Date debut : "+beginningProcessDate+"  Date fin : "+ endingProcessDate;
    	}
    }

    public DepthFirstSearcher(Graph graph) {
        date = 0;
        this.graph = graph;
        this.DepthFirstSearchInformations = new ArrayList<VertexInformations>();
        for(int i=0; i < graph.order(); i++)
            DepthFirstSearchInformations.add(i, new VertexInformations());
    }

    public void Search(){
        graph.iterEdges(new DepthSearchConsumer());
    }
    public void DepthFirstSearch(int vertex){
    	this.date +=1;
    	VertexInformations vertexInfo = DepthFirstSearchInformations.get(vertex);

    	vertexInfo.color = "gris";
    	vertexInfo.beginningProcessDate = this.date;
    	
    	ArrayList<Integer> AdjacentVertexes = graph.findAdjacentVertexes(vertex);
    	
    	
    	for(int adjacentVertex : AdjacentVertexes) {
    		if(DepthFirstSearchInformations.get(adjacentVertex).color == "blanc") {
    			System.out.println("Sommet : "+vertex+" a pour adj : "+adjacentVertex);
    			vertexInfo.ancestor = vertex;
    			DepthFirstSearch(adjacentVertex);
    		}
    	}
    }
    

    public void printDepthFirstSearchInformations(){
        System.out.println("size : "+DepthFirstSearchInformations.size());
        ListIterator list = DepthFirstSearchInformations.listIterator();
        while(list.hasNext())
            System.out.println(list.next().toString());
    }


    private class DepthSearchConsumer implements Graph.ArcConsumer{
        @Override
        public void apply(int source, int dest, Object o) {
            if(DepthFirstSearchInformations.get(source).color == "blanc")
                DepthFirstSearch(source);
        }
    }

}
