package mst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input.json";
        String outputFile = "output.json";

        try {
            System.out.println(" Loading input graphs from: " + inputFile);
            List<Graph> graphs = IOUtils.readInput(inputFile);
            System.out.println(" Total graphs detected: " + graphs.size());

            List<IOUtils.GraphResult> results = new ArrayList<>();

            for (Graph graph : graphs) {
                System.out.println("\n==============================");
                System.out.println(" Analyzing Graph #" + graph.getId());
                System.out.println(" Vertices: " + graph.getVertexCount() +
                        " | Edges: " + graph.getEdgeCount());

                if (!graph.isConnected()) {
                    System.out.println("⚠ Warning: Graph is not fully connected! MST may be incomplete.");
                }

                System.out.println("\n▶ Executing Prim's Algorithm...");
                MSTResult primResult = Prim.findMST(graph);
                printResult("Prim", primResult);

                System.out.println("\n▶ Executing Kruskal's Algorithm...");
                MSTResult kruskalResult = Kruskal.findMST(graph);
                printResult("Kruskal", kruskalResult);

                if (primResult.getTotalCost() == kruskalResult.getTotalCost()) {
                    System.out.println(" Matching MST cost for both algorithms!");
                } else {
                    System.out.println(" Cost mismatch detected! Check implementation!");
                }

                results.add(new IOUtils.GraphResult(
                        graph.getId(),
                        graph.getVertexCount(),
                        graph.getEdgeCount(),
                        primResult,
                        kruskalResult
                ));
            }

            System.out.println("\n Exporting results to: " + outputFile);
            IOUtils.writeOutput(outputFile, results);
            System.out.println(" Output successfully saved!");

        } catch (IOException e) {
            System.err.println(" File Processing Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printResult(String name, MSTResult result) {
        System.out.println("   • Total Cost: " + result.getTotalCost());
        System.out.println("   • Operations: " + result.getOperationsCount());
        System.out.println("   • Time: " +
                String.format("%.3f", result.getExecutionTimeMs()) + " ms");
        System.out.println("   • MST Edges: " + result.getEdgeCount());
    }
}
