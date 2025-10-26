package mst;

import java.util.*;


public class PrimAlgorithm {

    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        long operations = 0;

        Set<String> nodes = graph.getNodes();
        if (nodes.isEmpty()) {
            return new MSTResult(new ArrayList<>(), 0, operations, 0);
        }

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int totalCost = 0;

        String startNode = nodes.iterator().next();
        visited.add(startNode);
        operations++;

        for (Edge edge : graph.getAdjacencyList().get(startNode)) {
            pq.offer(edge);
            operations++;
        }

        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            Edge currentEdge = pq.poll();
            operations++;

            String nextNode = currentEdge.getTo();

            if (visited.contains(nextNode)) {
                operations++;
                continue;
            }

            mstEdges.add(currentEdge);
            totalCost += currentEdge.getWeight();
            visited.add(nextNode);
            operations++;

            for (Edge edge : graph.getAdjacencyList().get(nextNode)) {
                operations++;
                if (!visited.contains(edge.getTo())) {
                    pq.offer(edge);
                    operations++;
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, operations, executionTimeMs);
    }
}