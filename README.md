# Assignment 3: Optimization of a City Transportation Network
(Minimum Spanning Tree)<br>
This repository contains the solution for Assignment 3, which focuses on optimizing a city's transportation network using Minimum Spanning Tree (MST) algorithms.

Implementation and performance comparison of **Prim's** and **Kruskal's** algorithms for finding the Minimum Spanning Tree in weighted undirected graphs.

##  Project Structure

```
mst-assignment/
├── src/
│   ├── main/java/mst/
│   │   ├── Main.java             
│   │   ├── Graph.java             
│   │   ├── Edge.java          
│   │   ├── PrimAlgorithm.java     
│   │   ├── KruskalAlgorithm.java  
│   │   ├── MSTResult.java    
│   │   ├── DisjointSet.java      
│   │   └── IOUtils.java      
│   └── test/java/mst/
│       └── MSTTest.java          
├── input.json                     
├── output.json                    
└── pom.xml
└── readme.md
└── report.pdf
                   
```


---

## 📥 Input Format

Input contains multiple graph definitions:

```json
[
  {
    "vertices": 4,
    "edges": [
      {"u": "A", "v": "B", "w": 1},
      {"u": "B", "v": "C", "w": 2}
    ]
  }
]
```
Each graph includes:

Number of vertices
Undirected weighted edges


##  Output Format

```json
[
  {
    "graph": 1,
    "vertices": 4,
    "edges": 5,
    "prim": {
      "totalCost": 6,
      "operations": 20,
      "timeMs": 0.30
    },
    "kruskal": {
      "totalCost": 6,
      "operations": 41,
      "timeMs": 0.44
    }
  }
]

```

### How to Run
```
Compile and execute:

mvn compile
mvn exec:java -Dexec.mainClass="mst.Main"

```

### Run Tests

```bash
mvn test
```
##  Testing

Tests verify:

MST correctness (same cost)

Edge count equals V − 1

No cycles exist

Graph connectivity ensured

```bash
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

##  Performance Results

### Summary Table

| Graph | Vertices | Edges | Algorithm | Total Cost | Time (ms) | Operations |
| ----: | -------: | ----: | --------- | ---------: | --------: | ---------: |
|     1 |        4 |     5 | Prim      |          6 |      0.30 |         20 |
|     1 |        4 |     5 | Kruskal   |          6 |      0.44 |         41 |
|     2 |        5 |     7 | Prim      |         16 |      0.03 |         30 |
|     2 |        5 |     7 | Kruskal   |         16 |      0.03 |         60 |
|     3 |        6 |     9 | Prim      |         15 |      0.01 |         40 |
|     3 |        6 |     9 | Kruskal   |         15 |      0.02 |         80 |
|     4 |       10 |    18 | Prim      |         28 |      0.02 |         72 |
|     4 |       10 |    18 | Kruskal   |         28 |      0.04 |        152 |
|     5 |       15 |    27 | Prim      |         40 |      0.07 |        110 |
|     5 |       15 |    27 | Kruskal   |         40 |      0.05 |        239 |

### Algorithm Comparison

| Aspect             | Prim’s Algorithm   | Kruskal’s Algorithm    |
| ------------------ | ------------------ | ---------------------- |
| Time Complexity    | O((V + E) log V)   | O(E log E)             |
| Graph Preference   | Dense graphs       | Sparse graphs          |
| Strategy           | Greedy by vertices | Greedy by edges        |
| Key Data Structure | Priority Queue     | Union-Find             |
| Additional Sorting | Not required       | Sorting edges required |

 Key Findings:

 Both algorithms yield the same MST cost, confirming the correctness of their solutions.
 Prim's Algorithm demonstrates better performance on small and medium dense graphs (Graphs 1–4).
 Kruskal's Algorithm demonstrates advantages on sparse graphs with a large number of vertices (Graph 5).
 The number of operations in Prima is consistently lower, approximately 2 times lower than in Kruskal under the same conditions.

##  Algorithm Comparison

| Aspect | Prim's Algorithm | Kruskal's Algorithm |
|--------|------------------|---------------------|
| **Time Complexity** | O((V + E) log V) | O(E log E) |
| **Space Complexity** | O(V) | O(V + E) |
| **Best For** | Dense graphs | Sparse graphs |
| **Data Structure** | Priority Queue | Disjoint Set (Union-Find) |
| **Approach** | Vertex-based (greedy) | Edge-based (sorting) |



## Conclusions

Both algorithms consistently produce correct MSTs
 Prim performs significantly fewer operations across all datasets
 Kruskal becomes competitive for large sparse graphs
 Execution time differences grow with graph complexity

Best Uses

Prim: Dense, graph stored as adjacency list/matrix

Kruskal: Sparse, edges already sorted or streamed


