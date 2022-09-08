package chapter_28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex_07 {
    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {0, 1}, {0, 2},
                {1, 0}, {1, 3},
                {2, 0}, {2, 4},
                {3, 1},
                {4, 5},
                {5, 4}, {5, 3}
        };

        UnweightedGraphFindCycle<Integer> graph = new UnweightedGraphFindCycle<>(edges, 6);
        System.out.print("Vertices in a cycle starting from 0:");
        List<Integer> cycle = graph.getACycle(0);
        if (cycle != null) {
            for (int i: cycle) {
                System.out.print(" " + graph.getVertex(i));
            }
        } else {
            System.out.print(" [No cycle detected].");
        }
    }
}

class UnweightedGraphFindCycle<V> extends UnweightedGraph<V> {
    public UnweightedGraphFindCycle(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public List<Integer> getACycle(int u) {
        int[] parent = new int[getSize()];
        boolean[] isVisited;
        int cycleEndIndex = -1;
        for (Edge e: neighbors.get(u)) {
            isVisited = new boolean[getSize()];
            Arrays.fill(parent, -1);
            isVisited[u] = true;
            parent[e.v] = u;
            if ((cycleEndIndex = dfsForCycle(u, e.v, parent, isVisited)) != -1) break;
        }

        if (cycleEndIndex != -1) {
            List<Integer> list = new ArrayList<>(), returnList = new ArrayList<>();
            int i = cycleEndIndex;
            while (i != -1) {
                list.add(i);
                i = parent[i];
            }
            for (int j = list.size() -1; j >= 0; j--) {
                returnList.add(list.get(j));
            }
            return returnList;
        }

        return null;
    }

    private int dfsForCycle(int root, int v, int[] parent, boolean[] isVisited) {
        isVisited[v] = true; // Vertex v visited

        for (Edge e : neighbors.get(v)) { // Note that e.u is v
            if (e.v == root && parent[v] != root) {
                return v; // Found the end of a cycle
            } else if (!isVisited[e.v]){ // e.v is w in Listing 28.8
                parent[e.v] = v; // The parent of w is v
                return dfsForCycle(root, e.v, parent, isVisited);
            }
        }

        // DFS ends without finding an edge with root
        return -1;
    }
}
