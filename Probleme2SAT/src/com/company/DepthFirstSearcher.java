package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;




public class DepthFirstSearcher {

    private Graph graph;

    public void setGraph(Graph graph) {this.graph = graph;}

    private int date;
    private ArrayList<Vertex> DepthFirstSearchInformations;
    private ArrayList<ArrayList<Integer>> stronglyConnectedComponents;
    private int stronglyConnectedComponentsIndex;
    
    private class Vertex implements Comparable<Vertex>{
        public int indexVertex;
    	public String color;
    	public int beginningProcessDate;
    	public int endingProcessDate;
    	public int ancestor;
    	
    	public Vertex(int indexVertex){
    	    this.indexVertex = indexVertex;
    		color = "blanc";
    		ancestor = -2;
    	}
        /*public Vertex(int indexVertex, int endingProcessDate){
            this.indexVertex = indexVertex;
    	    this.endingProcessDate = endingProcessDate;
            color = "blanc";
        }*/
    	public String toString() {
    		return "Sommet : "+indexVertex+"  color : "+color+"  Ancetre : "+ancestor+"  Date debut : "+beginningProcessDate+"  Date fin : "+ endingProcessDate;
    	}

        @Override
        /*
        Sort by greater endingProcessDate, so min(List<Vertex>) return the Vertex with the highest endingProcessDate value.
         */
        public int compareTo(Vertex vertexInformation) {
            if(vertexInformation.color != "blanc" && this.color !="blanc")return  vertexInformation.endingProcessDate - this.endingProcessDate;
            if(vertexInformation.color != "blanc")return -1;
            if(this.color != "blanc")return 1;
            return vertexInformation.endingProcessDate - this.endingProcessDate;
        }
    }
    

    public DepthFirstSearcher(Graph graph) {
        date = 0;
        this.graph = graph;
        stronglyConnectedComponents = new ArrayList<ArrayList<Integer>>();

        stronglyConnectedComponentsIndex = -1;

        DepthFirstSearchInformations = new ArrayList<Vertex>();

        for(int i=0; i < graph.order(); i++)
            DepthFirstSearchInformations.add(i, new Vertex(i));
    }

    
    public void Search(){
        graph.iterEdges(new DepthSearchConsumer());
        System.out.println(this);
    }


    public void SearchByEndingDate(){

        removeDepthFirstSearchInformationsExceptEndingProcessDate();
        stronglyConnectedComponents.clear();

        System.out.println(this);
        date = 0;


        while(Collections.min(DepthFirstSearchInformations).color.equals("blanc")){
            Vertex greatestEndingDateVertex = Collections.min(DepthFirstSearchInformations);
            greatestEndingDateVertex.ancestor = -1;
            this.stronglyConnectedComponentsIndex += 1;
            stronglyConnectedComponents.add(new ArrayList<>());
            stronglyConnectedComponents.get(stronglyConnectedComponentsIndex).add(greatestEndingDateVertex.indexVertex);
            DepthFirstSearch(greatestEndingDateVertex.indexVertex, true);

        }
        System.out.println(this);
    }

    
    public void DepthFirstSearch(int vertex, boolean byEndingDate){
    	date++;
    	Vertex vertexInfo = DepthFirstSearchInformations.get(vertex);

    	vertexInfo.color = "gris";
    	vertexInfo.beginningProcessDate = date;
    	
    	
    	ArrayList<Integer> AdjacentVertexesIndexes = graph.findAdjacentVertexesIndex(vertex);
    	sortVertexesArray(AdjacentVertexesIndexes); // to search by ending date

    	for(int adjacentVertexIndex : AdjacentVertexesIndexes) {
    		if(DepthFirstSearchInformations.get(adjacentVertexIndex).color.equals("blanc")) {
    			DepthFirstSearchInformations.get(adjacentVertexIndex).ancestor = vertex;
    			if(byEndingDate) {
                    stronglyConnectedComponents.get(stronglyConnectedComponentsIndex).add(adjacentVertexIndex); // only usefull in the Depth first search by ending date
                }
    			DepthFirstSearch(adjacentVertexIndex, byEndingDate);
    		}
    	}
    	date++;
    	vertexInfo.endingProcessDate = date;
    }

    
    private class DepthSearchConsumer implements Graph.ArcConsumer{
        @Override
        public void apply(int source, int dest, Object o) {
            if(DepthFirstSearchInformations.get(source).color.equals("blanc")) {
            	DepthFirstSearchInformations.get(source).ancestor = -1;
                DepthFirstSearch(source, false);
            }
        }
    }

    public boolean isFormulaSatisfiableOnNonConvertedStronglyConnectedComponents(){
        for (ArrayList<Integer> stronglyConnectedComponent : stronglyConnectedComponents) {
            for(int vertexIndex : stronglyConnectedComponent) {
                for (int vertexIndex2 : stronglyConnectedComponent) {
                    if (vertexIndex + 1 == -(vertexIndex2 - graph.order())) {
                        System.out.println(vertexIndex+"(=x"+(vertexIndex + 1)+")" + " et " + vertexIndex2+"(=-x"+-(vertexIndex2 - graph.order())+")"+ " trouve dans une meme composante : ");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean isFormulaSatisfiable(){
        for (ArrayList<Integer> stronglyConnectedComponent : stronglyConnectedComponents) {
            for(int vertexIndex : stronglyConnectedComponent) {
                for (int vertexIndex2 : stronglyConnectedComponent) {
                    if (vertexIndex == -vertexIndex2) {
                        System.out.println("x" + vertexIndex+" et x"+ -vertexIndex
                        		+ " trouve dans une meme composante : ");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    public ArrayList<HashMap<Integer, Boolean>> valuesSatisfyingFormula (){
    	ArrayList<HashMap<Integer, Boolean>> valuesSatisfyingFormula = new ArrayList<HashMap<Integer, Boolean>>();
    	int index = 0;
    	    	
    	for (ArrayList<Integer> stronglyConnectedComponent : stronglyConnectedComponents) {
    		valuesSatisfyingFormula.add(new HashMap<Integer, Boolean>());
            for (int vertexIndex : stronglyConnectedComponent) {
            	//valuesSatisfyingFormula.get(index).put(vertexIndex);
            }
            index++;
        }
    	
    	return valuesSatisfyingFormula;
    }


    public void sortVertexesArray(ArrayList<Integer> AdjacentVertexesIndexes){
        AdjacentVertexesIndexes.sort((i1, i2) -> {
            Vertex vertexInformation = DepthFirstSearchInformations.get((int) i1);
            Vertex vertexInformation2 = DepthFirstSearchInformations.get((int) i2);

            return vertexInformation.compareTo(vertexInformation2);
        });
    }


    public void removeDepthFirstSearchInformationsExceptEndingProcessDate(){
        for (Vertex vertexInformations : DepthFirstSearchInformations) {
            vertexInformations.beginningProcessDate = 0;
            vertexInformations.color = "blanc";
            if (vertexInformations.ancestor == -2) vertexInformations.color = "gris";
        }
    }
    
    public void convertStronglyConnectedComponentsVertexIndexToFormulaVariable(){
    	for (ArrayList<Integer> stronglyConnectedComponent : stronglyConnectedComponents) {
            for(int i =0; i < stronglyConnectedComponent.size(); i++) {
            	if(stronglyConnectedComponent.get(i) < graph.order()/2)
            		stronglyConnectedComponent.set(i, stronglyConnectedComponent.get(i)+1) ;
            	else
            		stronglyConnectedComponent.set(i, stronglyConnectedComponent.get(i)-graph.order()) ;
            }
    	}
    }

    public void printStronglyConnectedComponents(){
        for (ArrayList<Integer> stronglyConnectedComponent : stronglyConnectedComponents) {
            System.out.println(" ");
            System.out.print("Composantes fortements connexes : ");
            for (int vertexIndex : stronglyConnectedComponent) {
                System.out.print(vertexIndex+" ");
            }
        }
        for(int i=0;i<3;i++)
            System.out.println();
    }
    
    
    public String toString() {
    	String returnedStr = "";
    	for(Vertex vertexInfo : DepthFirstSearchInformations)
    		returnedStr += vertexInfo+"\n";
    	return returnedStr;
    }




}
