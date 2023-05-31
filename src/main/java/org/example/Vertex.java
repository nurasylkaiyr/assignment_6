package org.example;
import java.util.HashMap;
import java.util.Map;
public class Vertex<V> {
    private V data; // The data associated with the vertex
    private Map<Vertex<V>, Double> adjacentVertices; // Map of adjacent vertices and their weights

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>(); // Initialize the map of adjacent vertices
    }
}
