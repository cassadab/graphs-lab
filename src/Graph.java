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
        int distanceSoFar = 0;
        LinkedList<Vertex> searchQueue = new LinkedList<>();
        LinkedList<Vertex> verticesTouched = new LinkedList<>();
        // mark all as unvisited
        for (Vertex v : vertexMap.values()) {
            v.setColor(Color.WHITE);
        }

        Vertex sourceVertex = vertexMap.get(source);
        sourceVertex.setColor(Color.GREY);
        searchQueue.offer(sourceVertex);
        verticesTouched.add(sourceVertex);

        while(searchQueue.peek() != null && distanceSoFar < maxDistance) {
            distanceSoFar++;
            Vertex v = searchQueue.poll();

            // visit all adjacent vertices
            for( Vertex adjVertex : edgesMap.get(v)){
                if (adjVertex.getColor() == Color.WHITE) {
                    adjVertex.setColor(Color.GREY);
                    searchQueue.offer(adjVertex);
                    verticesTouched.add(adjVertex);
                }
            }
            v.setColor(Color.BLACK);
        }

        return verticesTouched;
    }

    public void addVertex(int key, Vertex v, Set<Vertex> adjacentVertices) {
        vertexMap.put(key, v);
        edgesMap.put(v, adjacentVertices);
    }
}
