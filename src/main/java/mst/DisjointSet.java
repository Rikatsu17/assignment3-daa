package mst;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private long operations;

    public DisjointSet() {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
        this.operations = 0;
    }

    public void makeSet(String node) {
        parent.put(node, node);
        rank.put(node, 0);
        operations++;
    }

    public String find(String node) {
        operations++;
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public boolean union(String node1, String node2) {
        operations++;
        String root1 = find(node1);
        String root2 = find(node2);

        if (root1.equals(root2)) {
            return false;
        }

        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);

        if (rank1 > rank2) {
            parent.put(root2, root1);
        } else if (rank1 < rank2) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank1 + 1);
        }
        return true;
    }

    public long getOperations() {
        return operations;
    }
}