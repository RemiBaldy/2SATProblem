package com.company;



import java.util.ArrayList;
import java.util.LinkedList;



public class Graph<Label> {


    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size-1);
        for (int i = 0;i<cardinal ;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }
    
    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }
    


    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Graphe de ").append(cardinal).append(" sommets :\n");

        for (int i = 0; i<cardinal ;i++) {
            for (Edge e : incidency.get(i)) {

                result.append(e.source).append(" ").append(e.destination).append(" ").append(e.label.toString()).append("\n");
            }
        }
        return result.toString();
    }
    
    public ArrayList<Integer> findAdjacentVertexesIndex(int vertex){
    	ArrayList<Integer> AdjacentVertexes = new ArrayList<Integer>();
    	for(Edge e : incidency.get(vertex))
    		AdjacentVertexes.add(e.destination);
    	return AdjacentVertexes;
    }
    

    public interface ArcFunction<Label,K> {
        public K apply(int source, int dest, Label label, K accu);
    }

    public interface ArcConsumer<Label> {
        public void apply(int source, int dest, Label label);
    }

    public <K> K foldEdges(ArcFunction<Label,K> f, K init) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                init = f.apply(e.source, e.destination, e.label, init);
            }
        };
        return init;
    }

    public void iterEdges(ArcConsumer<Label> f) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                f.apply(e.source, e.destination, e.label);
            }
        }
    }
}
