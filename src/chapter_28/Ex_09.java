package chapter_28;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ex_09 {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1}, {0, 2},
                {1, 0}, {1, 3}, {1, 2},
                {2, 0}, {2, 3},
                {3, 1}, {3, 2}
        };

        UnweightedGraphGetBipartite<Integer> graph = new UnweightedGraphGetBipartite<>(edges, 4);
        boolean isBipartite = graph.isBipartite();
        System.out.println("Is this graph bipartite: " + isBipartite);
        if (isBipartite) {
            System.out.print("The two sets are: " + graph.getBipartite());
        }
    }
}

class UnweightedGraphGetBipartite<V> extends UnweightedGraph<V> {
    public UnweightedGraphGetBipartite(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public boolean isBipartite() {
        return getBipartite() != null;
    }

    public @Nullable List<List<Integer>> getBipartite() {
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
                        return null;
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

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(U);
        lists.add(V);
        return lists;
    }
}

