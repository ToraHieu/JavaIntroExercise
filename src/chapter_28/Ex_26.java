package chapter_28;

import java.util.List;

public class Ex_26 {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};

        // Edge array for graph in Figure 28.1
        int[][] edges = {
                {0, 1}, {0, 3}, {0, 5},
                {1, 0}, {1, 2}, {1, 3},
                {2, 1}, {2, 3}, {2, 4}, {2, 10},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
                {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
                {6, 5}, {6, 7},
                {7, 4}, {7, 5}, {7, 6}, {7, 8},
                {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
                {9, 8}, {9, 11},
                {10, 2}, {10, 4}, {10, 8}, {10, 11},
                {11, 8}, {11, 9}, {11, 10}
        };

        Graph<String> graph1 = new Ex26UnweightedGraph<>(vertices, edges);
        graph1.remove(0, 1); graph1.remove(1, 0);
        System.out.println("The number of vertices in graph1: "
                + graph1.getSize());
        System.out.println("The vertex with index 1 is "
                + graph1.getVertex(1));
        System.out.println("The index for Miami is " +
                graph1.getIndex("Miami"));
        System.out.println("The edges for graph1:");
        graph1.printEdges();

        // List of Edge objects for graph in Figure 30.3a
        String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
        java.util.ArrayList<Edge> edgeList
                = new java.util.ArrayList<>();
        edgeList.add(new Edge(0, 2));
        edgeList.add(new Edge(1, 2));
        edgeList.add(new Edge(2, 4));
        edgeList.add(new Edge(3, 4));
        // Create a graph with 5 vertices
        Graph<String> graph2 = new Ex26UnweightedGraph<>
                (java.util.Arrays.asList(names), edgeList);
        graph2.remove(1, 2);
        System.out.println("\nThe number of vertices in graph2: "
                + graph2.getSize());
        System.out.println("The edges for graph2:");
        graph2.printEdges();
    }
}

class Ex26UnweightedGraph<V> extends UnweightedGraph<V> {
    public Ex26UnweightedGraph() {
    }

    public Ex26UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex26UnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public Ex26UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex26UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    @Override
    public boolean remove(int u, int v) {
        if (u <= neighbors.size()) {
            for (Edge e : neighbors.get(u)) {
                if (e.v == v) {
                    neighbors.get(u).remove(e);
                    return true;
                }
            }
        }

        return false;
    }
}