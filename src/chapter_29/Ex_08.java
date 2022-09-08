package chapter_29;

import chapter_28.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex_08 {
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

        Ex08WeightedGraph<String> graph =
                new Ex08WeightedGraph<>(vertices, edges);
        List<Integer> list = graph.getShortestHamiltonianCycle();
        for (int i : list) {
            System.out.print(graph.getVertex(i) + " ");
        }
        System.out.print("\nThe total weight: " + graph.getTotalWeightOfAPath(list));

    }
}

/* Copied from UnweightedGraphWithHamiltonian
* with minor changes on the reorder method
* for sorting based on Weight instead of Degree */
class Ex08WeightedGraph<V> extends WeightedGraph<V> {
    public Ex08WeightedGraph() {
    }

    public Ex08WeightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public Ex08WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public Ex08WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        super(vertices, edges);
    }

    public Ex08WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /** Return a shortest cycle
     * Return null if no such cycle exists */
    public List<Integer> getShortestHamiltonianCycle() {
        return getHamiltonianCycle();
    }

    public double getTotalWeightOfAPath(List<Integer> list) {
        double total = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (Edge e: neighbors.get(i)) {
                if (e.v == list.get(i + 1)) {
                    total += ((WeightedEdge)e).weight;
                    break;
                }
            }
        }

        return total;
    }

    /**
     * @return Return a HamiltonianPath path from the specified vertex object
     * Return null if the graph does not contain a Hamiltonian path
     */
    public List<Integer> getHamiltonianPath(V vertex) {
        return getHamiltonianPathOrCycle(getIndex(vertex), false);
    }

    /**
     * @return Return a HamiltonianPath path from the specified vertex label
     * Return null if the graph does not contain a Hamiltonian path
     */
    public List<Integer> getHamiltonianPath(int index) {
        return getHamiltonianPathOrCycle(index, false);
    }

    /** Helper method for custom search Hamiltonian path or cycle */
    private List<Integer> getHamiltonianPathOrCycle(int index, boolean isSearchingForCycle) {
        /* A path starts from v. (i, next[i]) represents an edge in the path.
        isVisited[i] tracks whether i is currently in the path. */
        int[] next = new int[getSize()];
        // Indicate no subpath from i is found yet
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];

        // The vertices in the Hamiltonian path are stored in result
        List<Integer> result = null;

        /* To speed up search, reorder the adjacency list for each vertex
        so that the vertices in the list are in increasing order of their degrees */
        for (int i = 0; i < getSize(); i++) {
            reorderNeighborsOnWeight(neighbors.get(i));
        }

        if (getHamiltonianPathOrCycle(index, index, next, isVisited, isSearchingForCycle)) {
            result = new ArrayList<>();
            int vertex = index;
            while (vertex != -1) {
                result.add(vertex);
                vertex = next[vertex];
            }
        }

        return result;
    }

    /** Return a Hamiltonian cycle
     * Return null if the graph doesn't contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle() {
        return getHamiltonianPathOrCycle(0, true);
    }

    /** Return a Hamiltonian cycle that starts searching from a specific vertex index
     * Return null if the graph doesn't contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle(int v) {
        return getHamiltonianPathOrCycle(v, true);
    }

    /**
     * Reorder the adjacency list in increasing order of degrees
     */
    private void reorderNeighborsOnWeight(List<Edge> list) {
        for (int i = list.size() - 1; i >= 1; i--) {
            // Find the maximum in the list[0..i]
            double currentMaxWeight = ((WeightedEdge)list.get(0)).weight;
            int currentMaxIndex = 0;

            for (int j = 1; j <= i; j++) {
                if (currentMaxWeight < getDegree(list.get(j).v)) {
                    currentMaxWeight = getDegree(list.get(j).v);
                    currentMaxIndex = j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary
            if (currentMaxIndex != i) {
                Edge temp = list.get(currentMaxIndex);
                list.set(currentMaxIndex, list.get(i));
                list.set(i, temp);
            }
        }
    }

    /**
     * Return true if all elements in array isVisited are true
     */
    private boolean allVisited(boolean[] isVisited) {
        for (int i = 0; i < getSize(); i++) {
            if (!isVisited[i])
                return false;
        }

        return true;
    }

    /**
     * Search for a Hamiltonian path from v
     */
    private boolean getHamiltonianPathOrCycle(int root, int v, int[] next, boolean[] isVisited,
                                              boolean isSearchingForCycle) {
        isVisited[v] = true;

        if (allVisited(isVisited)) {
            if (!isSearchingForCycle)
                return true; // The path now includes all vertices, thus is found
            else {
                for (Edge e: neighbors.get(v)) {
                    if (e.v == root)
                        return true; // The last vertex connect with the root, thus a cycle is found
                }
                isVisited[v] = false; // Back track
                return false;
            }
        }

        for (int i = 0; i < neighbors.get(v).size(); i++) {
            int u = neighbors.get(v).get(i).v;
            if (!isVisited[u] && getHamiltonianPathOrCycle(root, u, next, isVisited, isSearchingForCycle)) {
                next[v] = u;
                return true;
            }
        }

        isVisited[v] = false;
        return false;
    }
}