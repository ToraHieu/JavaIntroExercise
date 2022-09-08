package chapter_29;

import chapter_28.Edge;

import java.util.ArrayList;
import java.util.List;

public class Ex_03 {
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

        Ex03WeightedGraphWithKruskalMST<String> graph1 =
                new Ex03WeightedGraphWithKruskalMST<>(vertices, edges);
        Ex03WeightedGraphWithKruskalMST<String>.MST tree1 = graph1.getKruskalMST();
        System.out.println("Total weight is " + tree1.getTotalWeight());
        tree1.printTree();

        edges = new int[][]{
                {0, 1, 2}, {0, 3, 8},
                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
                {4, 2, 5}, {4, 3, 6}
        };

        Ex03WeightedGraphWithKruskalMST<Integer> graph2 = new Ex03WeightedGraphWithKruskalMST<>(edges, 5);
        Ex03WeightedGraphWithKruskalMST<Integer>.MST tree2 =
                graph2.getKruskalMST(1);
        System.out.println("\nTotal weight is " + tree2.getTotalWeight());
        tree2.printTree();
    }
}

class Ex03WeightedGraphWithKruskalMST<V> extends WeightedGraph<V> {
    public Ex03WeightedGraphWithKruskalMST() {
    }

    public Ex03WeightedGraphWithKruskalMST(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex03WeightedGraphWithKruskalMST(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex03WeightedGraphWithKruskalMST(List<V> vertices, List<WeightedEdge> edges) {
        super(vertices, edges);
    }

    public Ex03WeightedGraphWithKruskalMST(List<WeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MST getKruskalMST() {
        return getKruskalMST(0);
    }


    /* Kruskal's algorithm */
    public MST getKruskalMST(int startingVertex) {
        List<Integer> T = new ArrayList<>();
        T.add(startingVertex);
        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;
        double totalWeight = 0;

        Double[][] m = getAdjacencyMatrix();

        while (T.size() < getSize()) {
            int minU = -1, minV = -1;
            double minWeight = Double.POSITIVE_INFINITY;
            for (int i : T) {
                for (int j = 0; j < m[i].length; j++) {
                    if (m[i][j] != null && !T.contains(j) && m[i][j] < minWeight) {
                        minWeight = m[i][j];
                        minU = i;
                        minV = j;
                    }
                }
            }

            if (minV != -1) {
                T.add(minV);
                parent[minV] = minU;
                totalWeight += minWeight;
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
