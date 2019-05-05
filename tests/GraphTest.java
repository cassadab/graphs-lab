import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph g;
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Vertex v4;
    private Vertex v5;

    /**
     * Sets up a small graph for testing
     * The graph looks like this:
     *                   1
     *                  / \
     *                2    3
     *                 \  /
     *                  4
     *                 /
     *                5
     */
    @BeforeEach
    void setUp() {
        g = new Graph();
        v1 = new Vertex(1);
        v2 = new Vertex(2);
        v3 = new Vertex(3);
        v4 = new Vertex(4);
        v5 = new Vertex(5);

        Set<Vertex> adj1 = new HashSet<>();
        adj1.add(v2);
        adj1.add(v3);

        Set<Vertex> adj2 = new HashSet<>();
        adj2.add(v1);
        adj2.add(v4);

        Set<Vertex> adj3 = new HashSet<>();
        adj3.add(v1);
        adj3.add(v4);

        Set<Vertex> adj4 = new HashSet<>();
        adj4.add(v2);
        adj4.add(v3);
        adj4.add(v5);

        Set<Vertex> adj5 = new HashSet<>();
        adj5.add(v4);

        g.addVertex(1, v1, adj1);
        g.addVertex(2, v2, adj2);
        g.addVertex(3, v3, adj3);
        g.addVertex(4, v4, adj4);
        g.addVertex(5, v5, adj5);
    }

    /**
     * Tests the breadth first traversal method. Asserts that the correct amount of vertices are visited based on
     * the max length given.
     */
    @Test
    void breadthFirst() {
        assertEquals(1, g.breadthFirst(1, 0).size());
        assertEquals(3, g.breadthFirst(1, 1).size());
        assertEquals(4, g.breadthFirst(1, 2).size());
        assertEquals(5, g.breadthFirst(1, 3).size());
        assertEquals(5, g.breadthFirst(1, 10).size());
        assertEquals(3, g.breadthFirst(3, 1).size());
     }

    @Test
    void recommendFriends() {

        Set<Edge> edges = g.recommendFriends(2);
        for (Edge e : edges){
            System.out.println(e.toString());
        }
        assertTrue(edges.contains(new Edge(v1, v4)));
        assertTrue(edges.contains(new Edge(v4, v1)));
        assertTrue(edges.contains(new Edge(v2, v3)));
        assertTrue(edges.contains(new Edge(v3, v2)));
        assertTrue(edges.contains(new Edge(v2, v5)));
        assertTrue(edges.contains(new Edge(v5, v2)));
        assertTrue(edges.contains(new Edge(v3, v5)));
        assertTrue(edges.contains(new Edge(v5, v3)));
        assertEquals(8, edges.size());
    }
}