package org.example;
import java.util.*;
public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyList; // Map of vertices and their adjacent vertices

    public WeightedGraph() {
        adjacencyList = new HashMap<>(); // Initialize the adjacency list
    }
    public void addVertex(Vertex<V> vertex) {
        // Add the vertex to the adjacency list
        // with an empty list of adjacent vertices
        adjacencyList.put(vertex, new LinkedList<>());
    }
}
