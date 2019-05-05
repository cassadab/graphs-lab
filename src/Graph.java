import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, Vertex> vertexMap;
    private Map<Integer, Set> edgesMap;

    public Graph() {
    }

    public Graph(Map<Integer, Vertex> vertexMap, Map<Integer, Set> edgesMap) {
        this.vertexMap = vertexMap;
        this.edgesMap = edgesMap;
    }

    public int BFS(int maxDistance, int target) {


        return -1;
    }
}
