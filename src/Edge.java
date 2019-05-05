import java.util.Objects;

public class Edge {

    private Vertex startVertex;
    private Vertex endVertex;

    public Edge(Vertex startVertex, Vertex endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    @Override
    public String toString() {
        return startVertex.getId() + " should be friends with " + endVertex.getId() + "!";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(startVertex, edge.startVertex) &&
                Objects.equals(endVertex, edge.endVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex);
    }
}
