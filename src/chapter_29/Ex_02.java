package chapter_29;

import chapter_28.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex_02 {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
                {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599},
                {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
                {4, 8, 864}, {4, 10, 496},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533},
                {5, 6, 983}, {5, 7, 787},
                {6, 5, 983}, {6, 7, 214},
                {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
                {8, 4, 864}, {8, 7, 888}, {8, 9, 661},
                {8, 10, 781}, {8, 11, 810},
                {9, 8, 661}, {9, 11, 1187},
                {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
                {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };

        Ex02WeightedGraph<String> graph1 =
                new Ex02WeightedGraph<>(vertices, edges);
        Ex02WeightedGraph<String>.MST tree1 = graph1.getMinimumSpanningTree();
        System.out.println("Total weight is " + tree1.getTotalWeight());
        tree1.printTree();

        edges = new int[][]{
                {0, 1, 2}, {0, 3, 8},
                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
                {4, 2, 5}, {4, 3, 6}
        };

        Ex02WeightedGraph<Integer> graph2 = new Ex02WeightedGraph<>(edges, 5);
        Ex02WeightedGraph<Integer>.MST tree2 =
                graph2.getMinimumSpanningTree(1);
        System.out.println("\nTotal weight is " + tree2.getTotalWeight());
        tree2.printTree();
    }
}

class Ex02WeightedGraph<V> extends WeightedGraph<V> {
    public Ex02WeightedGraph() {
    }

    public Ex02WeightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex02WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex02WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        super(vertices, edges);
    }

    public Ex02WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /** Get MST using adjacency matrix */
    @Override
    public MST getMinimumSpanningTree(int startingVertex) {
        List<Integer> T = new ArrayList<>();
        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[startingVertex] = 0;
        double totalWeight = 0;

        Double[][] m = getAdjacencyMatrix();

        while (T.size() < getSize()) {
            double min = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < min) {
                    min = cost[i];
                    u = i;
                }
            }

            if (u != -1) {
                T.add(u);
                totalWeight += min;
                for (int v = 0; v < m[u].length; v++) {
                    if (!T.contains(v) && m[u][v] != null && cost[v] > m[u][v]) {
                        cost[v] = m[u][v];
                        parent[v] = u;
                    }
                }
            } else break;
        }

        return new MST(startingVertex, parent, T, totalWeight);
    }

    private Double[][] getAdjacencyMatrix() {
        Double[][] m = new Double[getSize()][getSize()];
        for (List<Edge> list : neighbors) {
            for (Edge e: list) {
                m[e.u][e.v] = ((WeightedEdge)e).weight;
            }
        }
        return m;
    }
}