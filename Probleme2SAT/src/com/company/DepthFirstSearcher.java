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
    	//printDepthFirstSearchInformations();
        graph.iterEdges(new DepthSearchConsumer());
    }
    
    public void DepthFirstSearch(int vertex){
    	date++;
    	VertexInformations vertexInfo = DepthFirstSearchInformations.get(vertex);

    	vertexInfo.color = "gris";
    	vertexInfo.beginningProcessDate = date;
    	
    	
    	ArrayList<Integer> AdjacentVertexesIndexes = graph.findAdjacentVertexesIndex(vertex);
    	
    	
    	for(int adjacentVertexIndex : AdjacentVertexesIndexes) {
    		if(DepthFirstSearchInformations.get(adjacentVertexIndex).color == "blanc") {
    			DepthFirstSearchInformations.get(adjacentVertexIndex).ancestor = vertex;
    			System.out.println("Sommet : "+vertex+" a pour adj : "+adjacentVertexIndex);    			
    			DepthFirstSearch(adjacentVertexIndex);
    		}
    	}
    	date++;
    	vertexInfo.endingProcessDate = date;
    }
 
    
    private class DepthSearchConsumer implements Graph.ArcConsumer{
        @Override
        public void apply(int source, int dest, Object o) {
            if(DepthFirstSearchInformations.get(source).color == "blanc") {
            	DepthFirstSearchInformations.get(source).ancestor = -1;
                DepthFirstSearch(source);
            }
        }
    }
    
    
    
    public String toString() {
    	String returnedStr = new String();
    	int i=0;
    	for(VertexInformations vertexInfo : DepthFirstSearchInformations) {
    		returnedStr += "Vertex : "+i+" "+vertexInfo+"\n";
    		i++;
    	}
    	return returnedStr;
    }




}
