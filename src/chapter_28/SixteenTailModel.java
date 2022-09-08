package chapter_28;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SixteenTailModel {
    protected final static int NUMBER_OF_NODES = 65536; // 2^16
    protected final static int BOARD_LENGTH = 4;
    protected final static int BOARD_SIZE = BOARD_LENGTH * BOARD_LENGTH;
    protected UnweightedGraph<Integer>.SearchTree tree;

    /**
     * Construct a model
     */
    public SixteenTailModel() {
        // Create edges
        List<Edge> edges = getEdges();

        // Create a graph
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(
                edges, NUMBER_OF_NODES);

        // Obtain a BSF tree rooted at the target node
        tree = graph.bfs(NUMBER_OF_NODES - 1);
    }

    /**
     * Create all edges for the graph
     */
    private List<Edge> getEdges() {
        List<Edge> edges =
                new ArrayList<>(); // Store edges

        for (int u = 0; u < NUMBER_OF_NODES; u++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                char[] node = getNode(u); // Get the node for vertex u
                if (node[k] == 'H') {
                    int v = getFlippedNode(node, k);
                    // Add edge (v, u) for a legal move from node u to node v
                    edges.add(new Edge(v, u));
                }
            }
        }

        return edges;
    }

    public static int getFlippedNode(char[] node, int position) {
        int row = position / BOARD_LENGTH;
        int column = position % BOARD_LENGTH;

        flipACell(node, row, column);
        flipACell(node, row - 1, column);
        flipACell(node, row + 1, column);
        flipACell(node, row, column - 1);
        flipACell(node, row, column + 1);

        return getIndex(node);
    }

    public static void flipACell(char[] node, int row, int column) {
        if (row >= 0 && row < BOARD_LENGTH && column >= 0 && column < BOARD_LENGTH) {
            // Within the boundary
            if (node[row * BOARD_LENGTH + column] == 'H')
                node[row * BOARD_LENGTH + column] = 'T'; // Flip from H to T
            else
                node[row * BOARD_LENGTH + column] = 'H'; // Flip from T to H
        }
    }

    public static int getIndex(char[] node) {
        int result = 0;

        for (int i = 0; i < BOARD_SIZE; i++)
            if (node[i] == 'T')
                result = result * 2 + 1;
            else
                result = result * 2;

        return result;
    }

    public static char[] getNode(int index) {
        char[] result = new char[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            int digit = index % 2;
            if (digit == 0)
                result[BOARD_SIZE - 1 - i] = 'H';
            else
                result[BOARD_SIZE - 1 - i] = 'T';
            index = index / 2;
        }

        return result;
    }

    public @Nullable List<Integer> getShortestPath(int nodeIndex) {
        List<Integer> path = tree.getPath(nodeIndex);
        if (path.size() > 1 || path.get(0) == NUMBER_OF_NODES - 1)
            return path;
        return null;
    }

    public static void printNode(char[] node) {
        for (int i = 0; i < BOARD_SIZE; i++)
            if (i % BOARD_LENGTH != BOARD_LENGTH - 1)
                System.out.print(node[i]);
            else
                System.out.println(node[i]);

        System.out.println();
    }
}
