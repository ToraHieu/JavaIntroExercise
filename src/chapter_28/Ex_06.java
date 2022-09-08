package chapter_28;

import java.util.Arrays;

public class Ex_06 {
    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {0, 1}, {0, 2},
                {1, 0}, {1, 3},
                {2, 0}, {2, 4},
                {3, 1},
                {4, 5},
                {5, 4},
        };

        UnweightedGraphDetectCycle<Integer> graph = new UnweightedGraphDetectCycle<>(edges, 6);
        System.out.print("Is this graph cyclic: " + graph.isCyclic());
    }
}

/**
 * Algorithm for determining whether there is a cycle in the graph using DFS
 * DFS from a vertex v,
 * if DFS find an edge that connect to any ancestor of the current vertex,
 * except its direct parent, a circle is detected.
 */
class UnweightedGraphDetectCycle<V> extends UnweightedGraph<V> {
    public UnweightedGraphDetectCycle(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public boolean isCyclic() {
        int[] parent = new int[vertices.size()];
        // Initialize parent[i] to -1
        Arrays.fill(parent, -1);

        // Mark visited vertices
        boolean[] isSearched = new boolean[vertices.size()];

        // Recursively search
        for (int i = 0; i < isSearched.length; i++) {
            if (!isSearched[i]) {
                boolean[] isVisited = new boolean[vertices.size()];
                if (dfs(i, parent, isVisited, isSearched))
                    return true;
            }
        }

        return false;
    }

    /**
     * Recursive method for DFS search
     */
    private boolean dfs(int v, int[] parent, boolean[] isVisited, boolean[] isSearched) {
        isSearched[v] = true; // Vertex v is DFS-ed
        isVisited[v] = true; // Vertex v visited

        for (Edge e : neighbors.get(v)) { // Note that e.u is v
            if (isVisited[e.v]) {
                if (e.v != parent[v]) return true; // Found a back edge
            } else { // e.v is w in Listing 28.8
                parent[e.v] = v; // The parent of w is v
                if (dfs(e.v, parent, isVisited, isSearched)) // Recursive search
                    return true;
            }
        }

        // DFS ends without finding a back edge
        return false;
    }
}
