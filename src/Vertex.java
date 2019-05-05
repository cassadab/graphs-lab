public class Vertex {
    private Color color;
    private Vertex predecessor;

    public Vertex(Color color, Vertex predecessor) {
        this.color = color;
        this.predecessor = predecessor;
    }
}
