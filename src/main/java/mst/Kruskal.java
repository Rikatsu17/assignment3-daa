package mst;

import java.util.*;

public class Kruskal {

    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        long operations = 0;

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        DisjointSet ds = new DisjointSet();

        for (String node : graph.getNodes()) {
            ds.makeSet(node);
        }
        operations += ds.getOperations();

        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);
        operations += sortedEdges.size() * Math.log(sortedEdges.size());

        for (Edge edge : sortedEdges) {
            operations++;

            String node1 = edge.getFrom();
            String node2 = edge.getTo();

            String root1 = ds.find(node1);
            String root2 = ds.find(node2);

            operations++;
            if (!root1.equals(root2)) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                ds.union(node1, node2);

                if (mstEdges.size() == graph.getNodes().size() - 1) {
                    break;
                }
            }
        }

        operations += ds.getOperations();

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, operations, executionTimeMs);
    }
}