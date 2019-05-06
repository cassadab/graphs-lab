import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertexMap;
    private Map<Vertex, Set<Vertex>> edgesMap;

    public Graph() {
        this.vertexMap = new HashMap<>();
        this.edgesMap = new HashMap<>();
    }

    public Graph(Map<Integer, Vertex> vertexMap, Map<Vertex, Set<Vertex>> edgesMap) {
        this.vertexMap = vertexMap;
        this.edgesMap = edgesMap;
    }

    public List<Vertex> breadthFirst(int source, int maxDistance) {
        LinkedList<Vertex> searchQueue = new LinkedList<>();
        LinkedList<Vertex> verticesTouched = new LinkedList<>();
        // mark all as unvisited and reset their depths from source
        for (Vertex v : vertexMap.values()) {
            v.setColor(Color.WHITE);
            v.setDepth(0);
        }

        Vertex sourceVertex = vertexMap.get(source);
        sourceVertex.setColor(Color.GREY);
        searchQueue.offer(sourceVertex);

        while(searchQueue.peek() != null) {
            Vertex v = searchQueue.poll();
            if (v.getDepth() <= maxDistance) {
                // visit all adjacent vertices
                verticesTouched.add(v);
                for( Vertex adjVertex : edgesMap.get(v)){
                    if (adjVertex.getColor() == Color.WHITE) {
                        adjVertex.setDepth(v.getDepth() + 1);
                        adjVertex.setColor(Color.GREY);
                        searchQueue.offer(adjVertex);
                    }
                }
                v.setColor(Color.BLACK);
            }
        }

        return verticesTouched;
    }

    public Set<Edge> recommendFriends(int maxDistance) {
        Set<Edge> edges = new HashSet<>();

        List<Vertex> potentialFriends;
        for(Vertex person : vertexMap.values()) {
            potentialFriends = breadthFirst(person.getId(), maxDistance);

            Set<Vertex> currentFriends = edgesMap.get(person);

            for (Vertex potentialFriend : potentialFriends) {
                if (!currentFriends.contains(potentialFriend) && person.getId() != potentialFriend.getId()) {
                    edges.add(new Edge(person, potentialFriend));
                    edges.add(new Edge(potentialFriend, person));
                }
            }
        }

        return edges;
    }

    public void addVertex(int key, Vertex v, Set<Vertex> adjacentVertices) {
        vertexMap.put(key, v);
        edgesMap.put(v, adjacentVertices);
    }

    public boolean containsVertex(int id) {
        return vertexMap.get(id) != null;
    }

    public void addAdjacentVertex(int srcId, Vertex adjacentVertex) {
        Vertex v = vertexMap.get(srcId);
        Set<Vertex> edges = edgesMap.get(v);
        edges.add(adjacentVertex);
        edgesMap.put(v, edges);
    }

    public Vertex getVertex(int id) {
        return vertexMap.get(id);
    }
}
