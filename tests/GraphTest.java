import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph g;

    @BeforeEach
    void setUp() {
        g = new Graph();
    }

    /**
     * Tests the breadth first traversal method. Asserts that the correct amount of vertices are visited based on
     * the max length given.
     *
     * Example graph in this test looks like:
     *             1
     *            / \
     *          2    3
     *           \  /
     *            4
     */
    @Test
    void breadthFirst() {

        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Vertex v4 = new Vertex();

        Set<Vertex> adj1 = new HashSet<>();
        adj1.add(v2);
        adj1.add(v3);

        Set<Vertex> adj2 = new HashSet<>();
        adj2.add(v4);

        Set<Vertex> adj3 = new HashSet<>();
        adj3.add(v4);
        Set<Vertex> adj4 = new HashSet<>();

        g.addVertex(1, v1, adj1);
        g.addVertex(2, v2, adj2);
        g.addVertex(3, v3, adj3);
        g.addVertex(4, v4, adj4);

        assertEquals(1, g.breadthFirst(1, 0).size());
        assertEquals(3, g.breadthFirst(1, 1).size());
        assertEquals(4, g.breadthFirst(1, 2).size());
        assertEquals(4, g.breadthFirst(1, 10).size());

     }
}