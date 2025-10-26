# Minimum Spanning Tree (MST) Algorithm Comparison

Implementation and performance comparison of **Prim's** and **Kruskal's** algorithms for finding the Minimum Spanning Tree in weighted undirected graphs.

## 🚀 Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+

### Run the Project

```bash
# Clone the repository
git clone https://github.com/Ardakkkkk1/mst-assignment
cd mst-assignment

# Compile and run
mvn clean package
java -jar target/mst-assignment-1.0-SNAPSHOT.jar
```

### Run Tests

```bash
mvn test
```

## 📊 Performance Results

### Summary Table

| Graph | Vertices | Edges | Algorithm | Total Cost | Time (ms) | Operations |
|-------|----------|-------|-----------|------------|-----------|------------|
| 1 | 4 | 5 | **Prim** | 6 | 0.51 | 20 |
| 1 | 4 | 5 | **Kruskal** | 6 | 0.66 | 41 |
| 2 | 5 | 7 | **Prim** | 16 | 0.03 | 30 |
| 2 | 5 | 7 | **Kruskal** | 16 | 0.05 | 60 |
| 3 | 6 | 9 | **Prim** | 15 | 0.02 | 40 |
| 3 | 6 | 9 | **Kruskal** | 15 | 0.04 | 80 |
| 4 | 10 | 18 | **Prim** | 28 | 0.05 | 72 |
| 4 | 10 | 18 | **Kruskal** | 28 | 0.06 | 152 |
| 5 | 15 | 27 | **Prim** | 40 | 0.16 | 110 |
| 5 | 15 | 27 | **Kruskal** | 40 | 0.10 | 239 |

### Key Findings

✅ **Both algorithms produce identical MST costs** (correctness verified)  
⚡ **Prim's Algorithm**: Faster on small-medium dense graphs (Graphs 1-4)  
⚡ **Kruskal's Algorithm**: Faster on large sparse graphs (Graph 5)  
📈 **Prim performs 2× fewer operations** across all graph sizes

## 🔬 Algorithm Comparison

| Aspect | Prim's Algorithm | Kruskal's Algorithm |
|--------|------------------|---------------------|
| **Time Complexity** | O((V + E) log V) | O(E log E) |
| **Space Complexity** | O(V) | O(V + E) |
| **Best For** | Dense graphs | Sparse graphs |
| **Data Structure** | Priority Queue | Disjoint Set (Union-Find) |
| **Approach** | Vertex-based (greedy) | Edge-based (sorting) |

## 📁 Project Structure

```
mst-assignment/
├── src/
│   ├── main/java/mst/
│   │   ├── Main.java              # Entry point
│   │   ├── Graph.java             # Graph representation
│   │   ├── Edge.java              # Edge class
│   │   ├── PrimAlgorithm.java     # Prim's implementation
│   │   ├── KruskalAlgorithm.java  # Kruskal's implementation
│   │   ├── MSTResult.java         # Result container
│   │   ├── DisjointSet.java       # Union-Find data structure
│   │   └── JsonHandler.java       # JSON I/O handler
│   └── test/java/mst/
│       └── MSTTest.java           # Unit tests
├── input.json                     # Input graphs
├── output.json                    # Results (generated)
└── pom.xml                        # Maven configuration
```

## 📝 Input Format

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 1},
        {"from": "B", "to": "C", "weight": 2}
      ]
    }
  ]
}
```

## 📤 Output Format

```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 4,
        "edges": 5
      },
      "prim": {
        "mst_edges": [...],
        "total_cost": 6,
        "operations_count": 20,
        "execution_time_ms": 0.51
      },
      "kruskal": {
        "mst_edges": [...],
        "total_cost": 6,
        "operations_count": 41,
        "execution_time_ms": 0.66
      }
    }
  ]
}
```

## ✅ Testing

All automated tests verify:
- ✓ MST cost consistency between algorithms
- ✓ Correct edge count (V - 1)
- ✓ Acyclic property
- ✓ Graph connectivity
- ✓ Disconnected graph handling

```bash
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

## 🎯 Conclusions

**When to use Prim's Algorithm:**
- Dense graphs (many edges)
- Need fewer operations
- Memory-constrained environments

**When to use Kruskal's Algorithm:**
- Sparse graphs (few edges)
- Large-scale graphs
- Edges already available as a list





