package chapter_28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UnweightedGraphWithHamiltonian<V> extends UnweightedGraph<V> {
    public UnweightedGraphWithHamiltonian(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraphWithHamiltonian(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
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
            reorderNeighborsOnDegree(neighbors.get(i));
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
    private void reorderNeighborsOnDegree(List<Edge> list) {
        for (int i = list.size() - 1; i >= 1; i--) {
            // Find the maximum in the list[0..i]
            int currentMaxDegree = getDegree(list.get(0).v);
            int currentMaxIndex = 0;

            for (int j = 1; j <= i; j++) {
                if (currentMaxDegree < getDegree(list.get(j).v)) {
                    currentMaxDegree = getDegree(list.get(j).v);
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
