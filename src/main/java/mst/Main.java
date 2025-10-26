package mst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input.json";
        String outputFile = "output.json";

        try {
            System.out.println("Reading input from " + inputFile + "...");
            List<Graph> graphs = JsonHandler.readInput(inputFile);
            System.out.println("Loaded " + graphs.size() + " graph(s)");

            List<JsonHandler.GraphResult> results = new ArrayList<>();

            for (Graph graph : graphs) {
                System.out.println("\n--- Processing Graph " + graph.getId() + " ---");
                System.out.println("Vertices: " + graph.getVertexCount() + ", Edges: " + graph.getEdgeCount());

                if (!graph.isConnected()) {
                    System.out.println("WARNING: Graph is disconnected. MST may be incomplete.");
                }

                System.out.println("\nRunning Prim's Algorithm...");
                MSTResult primResult = PrimAlgorithm.findMST(graph);
                System.out.println("  Total Cost: " + primResult.getTotalCost());
                System.out.println("  Operations: " + primResult.getOperationsCount());
                System.out.println("  Execution Time: " + String.format("%.2f", primResult.getExecutionTimeMs()) + " ms");
                System.out.println("  MST Edges: " + primResult.getEdgeCount());

                System.out.println("\nRunning Kruskal's Algorithm...");
                MSTResult kruskalResult = KruskalAlgorithm.findMST(graph);
                System.out.println("  Total Cost: " + kruskalResult.getTotalCost());
                System.out.println("  Operations: " + kruskalResult.getOperationsCount());
                System.out.println("  Execution Time: " + String.format("%.2f", kruskalResult.getExecutionTimeMs()) + " ms");
                System.out.println("  MST Edges: " + kruskalResult.getEdgeCount());

                if (primResult.getTotalCost() == kruskalResult.getTotalCost()) {
                    System.out.println("\n✓ Both algorithms produced the same total cost");
                } else {
                    System.out.println("\n✗ WARNING: Algorithms produced different costs!");
                }

                results.add(new JsonHandler.GraphResult(
                        graph.getId(),
                        graph.getVertexCount(),
                        graph.getEdgeCount(),
                        primResult,
                        kruskalResult
                ));
            }

            System.out.println("\n\nWriting results to " + outputFile + "...");
            JsonHandler.writeOutput(outputFile, results);
            System.out.println("Done! Results saved to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}