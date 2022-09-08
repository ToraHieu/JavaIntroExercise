package chapter_28;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ex_08 {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1}, {0, 2},
                {1, 0},
                {2, 0},
        };

        UnweightedGraphTestBipartite<Integer> graph = new UnweightedGraphTestBipartite<>(edges, 3);
        System.out.print("Is this graph bipartite: " + graph.isBipartite());
    }
}

class UnweightedGraphTestBipartite<V> extends UnweightedGraph<V> {
    public UnweightedGraphTestBipartite(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public boolean isBipartite() {
        List<Integer> U = new ArrayList<>(), V = new ArrayList<>(),
                currentSet = U;
        Set<Integer> toBeCheckedNext = new HashSet<>(), checkingSet = new HashSet<>();

        if (getSize() > 0) {
            currentSet.add(0);
            checkingSet.add(0);
        }

        while (checkingSet.size() > 0) {
            for (int i : checkingSet) {
                for (int neighbor : getNeighbors(i)) {
                    if (currentSet.contains(neighbor))
                        return false;
                    toBeCheckedNext.add(neighbor);
                }
            }

            currentSet = currentSet == U ? V : U; // Switch current set
            toBeCheckedNext.removeAll(currentSet); //Remove the neighbors that are already checked

            checkingSet.clear();
            checkingSet.addAll(toBeCheckedNext);

            currentSet.addAll(toBeCheckedNext); // Add the vertices to be checked
            toBeCheckedNext.clear();
        }

        return true;
    }
}
