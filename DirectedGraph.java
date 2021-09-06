package com.hubspot.sessions;

import java.util.*;

/**
 * Implementation of finding cycle in a directed graph.
 */
public class DirectedGraph {
    private Vertex[] graph;
    private final int V;

    /**
     * The constructor to initialize the Directed graph.
     * @param V the number of vertices in the graph
     */
    public DirectedGraph(int V){
        this.V = V;
        this.graph = new Vertex[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new Vertex(i);
        }
    }

    /**
     * This method adds edge to the graph.
     * @param u the first vertex of the edge
     * @param v the second vertex of the edge
     * @throws IllegalArgumentException the invalid arguments exception
     */
    public void addEdge(int u, int v) throws IllegalArgumentException {
        if(u >= V || v >= V || u <0 || v<0){
            throw new IllegalArgumentException("u and v must be within the range of 0 to V");
        }
        graph[v].incrementInDegrees(1);
        graph[u].setOutgoingEdges(graph[v]);
    }

    /**
     * Checks if the graph contains a cycle.
     * @return the status of the graph
     */
    public boolean hasCycle(){

        for (Vertex v: graph) {
            Set<Integer> visited = new HashSet<>();
            if(hasCycle(v, visited))
                return true;
        }
        return false;
    }

    /**
     * Private helper method to find the cycle in the graph using a depth first search.
     * @param u the parent vertex of the graph
     * @param visited the visited set of vertices to find if a vertex is repeated in the path
     * @return the status of the graph
     */
    private boolean hasCycle(Vertex u, Set<Integer> visited){
        if(visited.contains(u.key)) {
            return  true;
        }
        visited.add(u.key);
        for (Vertex v: u.getOutgoingEdges()) {
            return hasCycle(v, visited);
        }
        return false;
    }

    /**
     * Representation of a vertex of a graph. A vertex is a node of the graph and contains,
     * key, in degrees, and outgoing edges.
     */
    public  static class Vertex{
        private final int key;
        private final Set<Vertex> outgoingEdges;
        private int inDegrees;

        /**
         * Constructor to initialize the vertex.
         * @param key the key of the vertex
         */
        public Vertex(int key) {
            this.key = key;
            this.outgoingEdges = new HashSet<>();
            this.inDegrees = 0;
        }

        public Set<Vertex> getOutgoingEdges() {
            return outgoingEdges;
        }

        public void setOutgoingEdges(Vertex outgoingVertex) {
            this.outgoingEdges.add(outgoingVertex);
        }

        public int getInDegrees() {
            return inDegrees;
        }

        public void incrementInDegrees(int inDegrees) {
            this.inDegrees += inDegrees;
        }

        public void decreaseInDegrees(int inDegrees) {
            this.inDegrees -= inDegrees;
        }

        public int getKey() {
            return key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, outgoingEdges, inDegrees);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            return key == other.key;
        }
    }

    /**
     * Main method for testing the graph and implementation.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(3);
        graph.addEdge(0, 1);
        //graph.addEdge(0, 2);
        graph.addEdge(0, 2);
        // graph.addEdge(2, 0);
        graph.addEdge(1, 0);
        //graph.addEdge(3, 3);
        //System.out.println( graph.toString());

        if(graph.hasCycle())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");
    }
}
