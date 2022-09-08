package chapter_29;

import chapter_28.SixteenTailModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeightedTailModel16 extends SixteenTailModel implements Serializable {
    public WeightedTailModel16() {
        // Create edges
        List<WeightedEdge> edges = getEdges();

        // Create a graph
        WeightedGraph<Integer> graph = new WeightedGraph<>(
                edges, NUMBER_OF_NODES);

        // Obtain a BSF tree rooted at the target node
        tree = graph.bfs(NUMBER_OF_NODES - 1);
    }

    /**
     * Create all edges for the graph
     */
    private List<WeightedEdge> getEdges() {
        List<WeightedEdge> edges =
                new ArrayList<>(); // Store edges

        for (int u = 0; u < NUMBER_OF_NODES; u++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                char[] node = getNode(u); // Get the node for vertex u
                if (node[k] == 'H') {
                    int v = getFlippedNode(node, k);

                    // Add edge (v, u) for a legal move from node u to node v
                    edges.add(new WeightedEdge(v, u, getNumberOfFlips(u, v)));
                }
            }
        }

        return edges;
    }

    private static int getNumberOfFlips(int u, int v) {
        char[] node1 = getNode(u);
        char[] node2 = getNode(v);

        int count = 0; // Count the number of different cells
        for (int i = 0; i < node1.length; i++)
            if (node1[i] != node2[i]) count++;

        return count;
    }

    public int getNumberOfFlips(int u) {
        return (int)((WeightedGraph<Integer>.ShortestPathTree)tree)
                .getCost(u);
    }
}
