package chapter_28;

import java.util.List;

public class Ex_16 {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1}, {0, 2},
                {1, 0}, {1, 3}, {1, 2},
                {2, 0}, {2, 3},
                {3, 1}, {3, 2}
        };

        UnweightedGraphWithRemove<Integer> graph = new UnweightedGraphWithRemove<>(edges, 4);
        Graph<Integer> g = maxInducedSubgraph(graph, 3);
        g.printEdges();

    }

    public static <V> Graph<V> maxInducedSubgraph(Graph<V> g, int k) {
        // Copy the graph
        Graph<V> graph = new UnweightedGraphWithRemove<>();
        for (int i = 0; i < g.getSize(); i++) {
            graph.addVertex(g.getVertex(i));
        }
        for (int i = 0; i < g.getSize(); i++) {
            for (int j : g.getNeighbors(i)) {
                graph.addEdge(i, j);
            }
        }

        boolean isCompleted = false;
        while (!isCompleted) {
            isCompleted = true;
            for (int i = 0; i < graph.getSize(); i++) {
                if (graph.getDegree(i) < k) {
                    graph.remove(graph.getVertex(i));
                    isCompleted = false;
                    break;
                }
            }
        }

        return graph;
    }
}

class UnweightedGraphWithRemove<V> extends UnweightedGraph<V> {
    public UnweightedGraphWithRemove() {
    }

    public UnweightedGraphWithRemove(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraphWithRemove(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public UnweightedGraphWithRemove(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraphWithRemove(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    @Override
    public boolean remove(V v) {
        if (!vertices.contains(v))
            return false;

        int index = this.getIndex(v);
        vertices.remove(v);
        neighbors.remove(index);

        // Remove the edges adjacent to v
        for (List<Edge> list : this.neighbors) {
            for (int i = 0; i < list.size(); ) {
                if (list.get(i).v == index) {
                    list.remove(i);
                } else {
                    i++;
                }
            }
        }

        // Reassign labels to vertices after index
        for (List<Edge> list : this.neighbors) {
            for (Edge edge : list) {
                if (edge.u >= index) {
                    edge.u = edge.u - 1;
                }
                if (edge.v >= index) {
                    edge.v = edge.v - 1;
                }
            }
        }

        return true;
    }

    @Override
    public boolean remove(int u, int v) {
        List<Edge> edges = neighbors.get(u);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).v == v) {
                edges.remove(i);
                return true;
            }
        }
        return false;
    }
}
