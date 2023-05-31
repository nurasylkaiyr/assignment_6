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

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertex(source); // Check if the source vertex exists in the graph
        validateVertex(destination); // Check if the destination vertex exists in the graph

        source.addAdjacentVertex(destination, weight); // Add the adjacent vertex with the weight to the source vertex
        destination.addAdjacentVertex(source, weight); // Add the adjacent vertex with the weight to the destination vertex

        adjacencyList.get(source).add(destination); // Add the destination vertex to the source vertex's list of adjacent vertices
        adjacencyList.get(destination).add(source); // Add the source vertex to the destination vertex's list of adjacent vertices
    }
    private void validateVertex(Vertex<V> vertex) {
        if (!adjacencyList.containsKey(vertex))
            throw new IllegalArgumentException("Vertex " + vertex + " is not in the graph");
    }

    public boolean hasEdge(Vertex<V> source, Vertex<V> destination) {
        validateVertex(source); // Check if the source vertex exists in the graph
        validateVertex(destination); // Check if the destination vertex exists in the graph

        // Check if the source vertex has the destination vertex as an adjacent vertex
        return source.getAdjacentVertices().containsKey(destination);
    }

    public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
        validateVertex(vertex); // Check if the vertex exists in the graph
        return adjacencyList.get(vertex);
    }

    public void BFS(Vertex<V> start) {
        Map<Vertex<V>, Boolean> visited = new HashMap<>(); // Map to track visited vertices
        for (Vertex<V> vertex : adjacencyList.keySet()) {
            visited.put(vertex, false); // Initialize all vertices as not visited
        }

        Queue<Vertex<V>> queue = new LinkedList<>(); // Queue to perform breadth-first search
        visited.put(start, true); // Mark the start vertex as visited
        queue.add(start); // Add the start vertex to the queue

        while (!queue.isEmpty()) {
            Vertex<V> vertex = queue.poll(); // Retrieve and remove the vertex from the queue
            System.out.print(vertex.getData() + " "); // Process the vertex

            List<Vertex<V>> neighbors = adjacencyList.get(vertex); // Get the list of adjacent vertices
            for (Vertex<V> neighbor : neighbors) {
                if (!visited.get(neighbor)) { // If the neighbor is not visited
                    visited.put(neighbor, true); // Mark the neighbor as visited
                    queue.add(neighbor); // Add the neighbor to the queue for further exploration
                }
            }
        }
    }

    public Map<Vertex<V>, Double> Dijkstra(Vertex<V> start) {
        Map<Vertex<V>, Double> distances = new HashMap<>(); // Map to store the distances from the start vertex
        for (Vertex<V> vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.POSITIVE_INFINITY); // Initialize all distances to infinity
        }
        distances.put(start, 0.0); // Set the distance of the start vertex to 0

        // Priority queue to store vertices based on their distances
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        // Add the start vertex to the priority queue
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> vertex = priorityQueue.poll(); // Retrieve and remove the vertex with the minimum distance
            double distance = distances.get(vertex); // Get the distance of the vertex

            for (Map.Entry<Vertex<V>, Double> entry : vertex.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey(); // Get the adjacent vertex
                double weight = entry.getValue(); // Get the weight of the edge to the adjacent vertex
                double newDistance = distance + weight; // Calculate the new distance

                if (newDistance < distances.get(neighbor)) { // If the new distance is shorter than the current distance
                    priorityQueue.remove(neighbor); // Remove the neighbor from the priority queue
                    distances.put(neighbor, newDistance); // Update the distance to the neighbor
                    priorityQueue.add(neighbor); // Add the neighbor back to the priority queue for further evaluation
                }
            }
        }
        return distances;
    }

    public void printGraph() {
        for (Map.Entry<Vertex<V>, List<Vertex<V>>> entry : adjacencyList.entrySet()) {
            Vertex<V> vertex = entry.getKey(); // Get the vertex
            List<Vertex<V>> neighbors = entry.getValue(); // Get the list of adjacent vertices
            System.out.print("Vertex " + vertex.getData() + " is connected to: ");
            for (Vertex<V> neighbor : neighbors) {
                System.out.print(neighbor.getData() + " ");
            }
            System.out.println();
        }
    }

}
