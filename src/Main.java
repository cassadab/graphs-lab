import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g = parseFBData(g, "training_set.tsv");
        Set<Edge> testEdges = parseTestingSet("testing_set.tsv");
        if (g != null) {
            Set<Edge> friendsDist2 = g.recommendFriends(2);
            Set<Edge> friendsDist5 = g.recommendFriends(5);

            testAnalysis(testEdges, friendsDist2, "Max Length = 2");
            testAnalysis(testEdges, friendsDist5, "Max Length = 5");

        }
    }

    public static Graph parseFBData(Graph g, String fileName) {
        try {
            File trainingSet = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(trainingSet));
            String line;
            while((line = br.readLine()) != null) {
                String[] splitLine = line.split("\t");
                int srcId = Integer.parseInt(splitLine[0]);
                int destId = Integer.parseInt(splitLine[1]);

                // if destination vertex is missing, add it
                Vertex adjacentVertex;
                if (g.containsVertex(destId)) {
                    adjacentVertex = g.getVertex(destId);
                } else  {
                    adjacentVertex = new Vertex(destId);
                    Set<Vertex> adjSet= new HashSet<>();
                    g.addVertex(destId, adjacentVertex, adjSet);
                }

                if (g.containsVertex(srcId)) {
                    g.addAdjacentVertex(srcId, adjacentVertex);
                } else {
                    Vertex srcVertex = new Vertex(srcId);
                    Set<Vertex> adjSet = new HashSet<>();
                    adjSet.add(adjacentVertex);
                    g.addVertex(srcId, srcVertex, adjSet);
                }

            }
            return g;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return null;
        }
    }

    public static Set<Edge> parseTestingSet(String fileName) {
        try {
            Set<Edge> edges = new HashSet<>();
            File testingSet = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(testingSet));
            String line;
            while((line = br.readLine()) != null) {
                String[] splitLine = line.split("\t");
                int srcId = Integer.parseInt(splitLine[0]);
                int destId = Integer.parseInt(splitLine[1]);
                Vertex src = new Vertex(srcId);
                Vertex dest = new Vertex(destId);
                Edge e = new Edge(src, dest);
                edges.add(e);
            }
            return edges;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return null;
        }
    }

    public static double calculatePrecision(int falsePositives, int truePositives) {
        return((double)truePositives / ((double)truePositives + (double)falsePositives));
    }

    public static double calculateRecall(int truePositives, int falseNegatives) {
        return((double) truePositives / (double)(truePositives + falseNegatives));
    }

    public static void testAnalysis(Set<Edge> testEdges, Set<Edge> predictedEdges, String testTitle) {
        int truePositives = 0;
        int falsePositives = 0;
        int falseNegatives = 0;
        for(Edge e : predictedEdges) {
            if (testEdges.contains(e)) {
                truePositives++;
            } else {
                falsePositives++;
            }
        }
        for (Edge e : testEdges) {
            if (!predictedEdges.contains(e)) {
                falseNegatives++;
            }
        }
//            falsePositives = friendsDist2.size() - truePositives;
        System.out.println("\nTest Analysis for " + testTitle + ":");
        System.out.println("True Positives for " + testTitle + " : " + truePositives);
        System.out.println("False Positives for " + testTitle + ": " + falsePositives);
        System.out.println("Precision: " + calculatePrecision(falsePositives, truePositives));
        System.out.println("Recall: " + calculateRecall(truePositives, falseNegatives));
    }
}
