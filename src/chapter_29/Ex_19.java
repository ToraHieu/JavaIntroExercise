package chapter_29;

import chapter_26.AVLTree;
import chapter_28.Edge;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Ex_19 {
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

        Ex19WeightedGraph<String> graph1 =
                new Ex19WeightedGraph<>(vertices, edges);
        Ex19WeightedGraph<String>.ShortestPathTree tree1 =
                graph1.getShortestPath(graph1.getIndex("Chicago"));
        tree1.printAllPaths();

        // Display shortest paths from Houston to Chicago
        System.out.print("Shortest path from Houston to Chicago: ");
        java.util.List<String> path
                = tree1.getPath(graph1.getIndex("Houston"));
        for (String s: path) {
            System.out.print(s + " ");
        }

        edges = new int[][] {
                {0, 1, 2}, {0, 3, 8},
                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
                {4, 2, 5}, {4, 3, 6}
        };
        Ex19WeightedGraph<Integer> graph2 = new Ex19WeightedGraph<>(edges, 5);
        Ex19WeightedGraph<Integer>.ShortestPathTree tree2 =
                graph2.getShortestPath(3);
        System.out.println("\n");
        tree2.printAllPaths();
    }
}

class Ex19WeightedGraph<V> extends WeightedGraph<V> {
    public Ex19WeightedGraph() {
    }

    public Ex19WeightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex19WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex19WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        super(vertices, edges);
    }

    public Ex19WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    static class WeightedVertex implements Comparable<WeightedVertex> {
        final int index;
        double cost;

        public WeightedVertex(int index, Double cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(@NotNull Ex19WeightedGraph.WeightedVertex o) {
            return (int) (this.cost - o.cost);
        }
    }

    /** Find single source shortest paths */
    @Override
    public ShortestPathTree getShortestPath(int sourceVertex) {
        // cost[v] stores the cost of the path from v to the source
        // Initial cost set to infinity
        WeightedVertex[] vertices = new WeightedVertex[getSize()];

        for (int i = 0; i < getSize(); i++) {
            vertices[i] = new WeightedVertex(i, Double.POSITIVE_INFINITY);
//            tree.add(vertices[i]);
        }
        vertices[sourceVertex].cost = 0d;

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; // The parent of source is set to -1

        // T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();

        // Using AVLTree to find the smallest cost
        AVLTree<WeightedVertex> tree = new AVLTree<>();
        // Expand T
        while (T.size() < getSize()) {
            tree.clear();
            for (WeightedVertex vertex: vertices) {
                if (!T.contains(vertex.index)) {
                    tree.add(vertex);
                }
            }

            // Find smallest cost v in V - T
            int u; // Vertex to be determined
            if (tree.size() == 0) break;
            WeightedVertex v = tree.iterator().next();
            if (v.cost != Double.POSITIVE_INFINITY) {
                u = v.index;
            } else break;

            T.add(u); // Add a new vertex to T

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v)
                        && vertices[e.v].cost > vertices[u].cost + ((WeightedEdge)e).weight) {
                    vertices[e.v].cost = vertices[u].cost + ((WeightedEdge)e).weight;
                    parent[e.v] = u;
                }
            }
        } // End of while

        double[] cost = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            cost[i] = vertices[i].cost;
        }

        // Create a ShortestPathTree
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }
}

