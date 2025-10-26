package mst;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class JsonHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Graph> readInput(String filename) throws IOException {
        List<Graph> graphs = new ArrayList<>();

        try (Reader reader = new FileReader(filename)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray graphsArray = root.getAsJsonArray("graphs");

            for (JsonElement graphElement : graphsArray) {
                JsonObject graphObj = graphElement.getAsJsonObject();

                int id = graphObj.get("id").getAsInt();

                List<String> nodes = new ArrayList<>();
                JsonArray nodesArray = graphObj.getAsJsonArray("nodes");
                for (JsonElement nodeElement : nodesArray) {
                    nodes.add(nodeElement.getAsString());
                }

                List<Edge> edges = new ArrayList<>();
                JsonArray edgesArray = graphObj.getAsJsonArray("edges");
                for (JsonElement edgeElement : edgesArray) {
                    JsonObject edgeObj = edgeElement.getAsJsonObject();
                    String from = edgeObj.get("from").getAsString();
                    String to = edgeObj.get("to").getAsString();
                    int weight = edgeObj.get("weight").getAsInt();
                    edges.add(new Edge(from, to, weight));
                }

                graphs.add(new Graph(id, nodes, edges));
            }
        }

        return graphs;
    }

    public static void writeOutput(String filename, List<GraphResult> results) throws IOException {
        JsonObject root = new JsonObject();
        JsonArray resultsArray = new JsonArray();

        for (GraphResult result : results) {
            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("graph_id", result.graphId);

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", result.vertices);
            inputStats.addProperty("edges", result.edges);
            resultObj.add("input_stats", inputStats);

            resultObj.add("prim", createAlgorithmResult(result.primResult));

            resultObj.add("kruskal", createAlgorithmResult(result.kruskalResult));

            resultsArray.add(resultObj);
        }

        root.add("results", resultsArray);

        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(root, writer);
        }
    }

    private static JsonObject createAlgorithmResult(MSTResult result) {
        JsonObject algResult = new JsonObject();

        JsonArray mstEdges = new JsonArray();
        for (Edge edge : result.getMstEdges()) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", edge.getFrom());
            edgeObj.addProperty("to", edge.getTo());
            edgeObj.addProperty("weight", edge.getWeight());
            mstEdges.add(edgeObj);
        }

        algResult.add("mst_edges", mstEdges);
        algResult.addProperty("total_cost", result.getTotalCost());
        algResult.addProperty("operations_count", result.getOperationsCount());
        algResult.addProperty("execution_time_ms", Math.round(result.getExecutionTimeMs() * 100.0) / 100.0);

        return algResult;
    }

    public static class GraphResult {
        int graphId;
        int vertices;
        int edges;
        MSTResult primResult;
        MSTResult kruskalResult;

        public GraphResult(int graphId, int vertices, int edges,
                           MSTResult primResult, MSTResult kruskalResult) {
            this.graphId = graphId;
            this.vertices = vertices;
            this.edges = edges;
            this.primResult = primResult;
            this.kruskalResult = kruskalResult;
        }
    }
}