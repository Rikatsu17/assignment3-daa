package mst;

import java.util.*;

public class Graph {
    private int id;
    private Set<String> nodes;
    private List<Edge> edges;
    private Map<String, List<Edge>> adjacencyList;

    public Graph(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = new HashSet<>(nodes);
        this.edges = new ArrayList<>(edges);
        this.adjacencyList = new HashMap<>();
        buildAdjacencyList();
    }

    private void buildAdjacencyList() {
        for (String node : nodes) {
            adjacencyList.put(node, new ArrayList<>());
        }
        for (Edge edge : edges) {
            adjacencyList.get(edge.getFrom()).add(edge);
            adjacencyList.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }
    }

    public int getId() {
        return id;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getVertexCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public boolean isConnected() {
        if (nodes.isEmpty()) return true;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = nodes.iterator().next();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (Edge edge : adjacencyList.get(current)) {
                if (!visited.contains(edge.getTo())) {
                    visited.add(edge.getTo());
                    queue.add(edge.getTo());
                }
            }
        }

        return visited.size() == nodes.size();
    }
}