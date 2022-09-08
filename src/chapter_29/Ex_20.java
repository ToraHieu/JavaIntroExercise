package chapter_29;

import chapter_28.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex_20 {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};
        int[][] edges = {
                {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
                {6, 5, 983}, {6, 7, 214},
                {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
                {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
                {9, 8, 661}, {9, 11, 1187},
                {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
                {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };
        WeightedGraph<String> graph1 =
                new WeightedGraph<>(vertices, edges);

        long start, end;
        start = System.nanoTime();
        WeightedGraph<String>.MST tree1 = graph1.getMinimumSpanningTree();
        System.out.println("Total weight is " + tree1.getTotalWeight());
        tree1.printTree();
        WeightedGraph<String>.ShortestPathTree tree2 =
                graph1.getShortestPath(graph1.getIndex("Chicago"));
        tree2.printAllPaths();
        end = System.nanoTime();
        System.out.println(end - start);

        Ex20WeightedGraph<String> graph2 =
                new Ex20WeightedGraph<>(vertices, edges);
        start = System.nanoTime();
        WeightedGraph<String>.MST tree3 = graph2.getMinimumSpanningTree();
        System.out.println("Total weight is " + tree3.getTotalWeight());
        tree3.printTree();
        WeightedGraph<String>.ShortestPathTree tree4 =
                graph2.getShortestPath(graph2.getIndex("Chicago"));
        tree4.printAllPaths();
        end = System.nanoTime();
        System.out.println(end - start);
    }
}

class Ex20WeightedGraph<V> extends WeightedGraph<V> {
    public Ex20WeightedGraph() {
    }

    public Ex20WeightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex20WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex20WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        super(vertices, edges);
    }

    public Ex20WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    @Override
    public MST getMinimumSpanningTree(int startingVertex) {
        // cost[v] stores the cost by adding v to the tree
        double[] cost = new double[getSize()];

        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost
        }
        cost[startingVertex] = 0; // Cost of source is 0

        int[] parent = new int[getSize()]; // Parent of a vertex
        Arrays.fill(parent, -1);
        double totalWeight = 0; // Total weight of the tree thus far

        List<Integer> T = new ArrayList<>();
        boolean[] isInT = new boolean[getSize()];

        // Expand T
        while (T.size() < getSize()) {
            // Find smallest cost u in V - T
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!isInT[i] && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            if (u == -1) break;
            T.add(u); // Add a new vertex to T
            isInT[u] = true;
            totalWeight += cost[u]; // Add cost[u] to the tree

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!isInT[e.v] && cost[e.v] > ((WeightedEdge)e).weight) {
                    cost[e.v] = ((WeightedEdge)e).weight;
                    parent[e.v] = u;
                }
            }
        } // End of while

        return new MST(startingVertex, parent, T, totalWeight);    }

    @Override
    public ShortestPathTree getShortestPath(int sourceVertex) {
// cost[v] stores the cost of the path from v to the source
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
        }
        cost[sourceVertex] = 0; // Cost of source is 0

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; // The parent of source is set to -1

        // T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();
        boolean[] isInT = new boolean[getSize()];

        // Expand T
        while (T.size() < getSize()) {
            // Find smallest cost v in V - T
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!isInT[i] && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            if (u == -1) break;
            T.add(u); // Add a new vertex to T
            isInT[u] = true;

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!isInT[e.v]
                        && cost[e.v] > cost[u] + ((WeightedEdge)e).weight) {
                    cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
                    parent[e.v] = u;
                }
            }
        } // End of while

        // Create a ShortestPathTree
        return new ShortestPathTree(sourceVertex, parent, T, cost);    }
}
