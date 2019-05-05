public class Vertex {
    private Color color;
    private int id;
    private int depth;

    public Vertex(int id) {
        this.color = Color.WHITE;
        this.id = id;
        depth = 0;
    }

    public Vertex(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
