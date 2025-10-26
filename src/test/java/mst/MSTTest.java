package mst;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class MSTTest {

    @Test
    public void testSameTotalCost() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost(),
                "Prim and Kruskal should produce the same total cost");
    }

    @Test
    public void testCorrectNumberOfEdges() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        int expectedEdges = graph.getVertexCount() - 1;
        assertEquals(expectedEdges, primResult.getEdgeCount(),
                "Prim's MST should have V-1 edges");
        assertEquals(expectedEdges, kruskalResult.getEdgeCount(),
                "Kruskal's MST should have V-1 edges");
    }

    @Test
    public void testMSTIsAcyclic() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(isAcyclic(primResult.getMstEdges(), graph.getNodes()),
                "Prim's MST should be acyclic");
        assertTrue(isAcyclic(kruskalResult.getMstEdges(), graph.getNodes()),
                "Kruskal's MST should be acyclic");
    }

    @Test
    public void testMSTConnectsAllVertices() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(isConnected(primResult.getMstEdges(), graph.getNodes()),
                "Prim's MST should connect all vertices");
        assertTrue(isConnected(kruskalResult.getMstEdges(), graph.getNodes()),
                "Kruskal's MST should connect all vertices");
    }

    @Test
    public void testNonNegativeExecutionTime() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(primResult.getExecutionTimeMs() >= 0,
                "Execution time should be non-negative");
        assertTrue(kruskalResult.getExecutionTimeMs() >= 0,
                "Execution time should be non-negative");
    }

    @Test
    public void testNonNegativeOperationCount() {
        Graph graph = createSimpleGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        assertTrue(primResult.getOperationsCount() > 0,
                "Operation count should be positive");
        assertTrue(kruskalResult.getOperationsCount() > 0,
                "Operation count should be positive");
    }

    @Test
    public void testReproducibility() {
        Graph graph = createSimpleGraph();

        MSTResult primResult1 = PrimAlgorithm.findMST(graph);
        MSTResult primResult2 = PrimAlgorithm.findMST(graph);

        assertEquals(primResult1.getTotalCost(), primResult2.getTotalCost(),
                "Results should be reproducible");
    }

    @Test
    public void testDisconnectedGraph() {
        Graph graph = createDisconnectedGraph();
        MSTResult primResult = PrimAlgorithm.findMST(graph);
        MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);

        // For disconnected graph, MST should have fewer than V-1 edges
        assertTrue(primResult.getEdgeCount() < graph.getVertexCount() - 1,
                "Disconnected graph should have incomplete MST");
        assertTrue(kruskalResult.getEdgeCount() < graph.getVertexCount() - 1,
                "Disconnected graph should have incomplete MST");
    }


    private Graph createSimpleGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
        );
        return new Graph(1, nodes, edges);
    }

    private Graph createDisconnectedGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("C", "D", 2)
        );
        return new Graph(2, nodes, edges);
    }

    private boolean isAcyclic(List<Edge> edges, Set<String> nodes) {
        return edges.size() <= nodes.size() - 1;
    }

    private boolean isConnected(List<Edge> edges, Set<String> allNodes) {
        if (edges.isEmpty() && allNodes.size() > 1) return false;

        Map<String, List<String>> adj = new HashMap<>();
        for (String node : allNodes) {
            adj.put(node, new ArrayList<>());
        }

        for (Edge edge : edges) {
            adj.get(edge.getFrom()).add(edge.getTo());
            adj.get(edge.getTo()).add(edge.getFrom());
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = allNodes.iterator().next();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String neighbor : adj.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == allNodes.size();
    }
}