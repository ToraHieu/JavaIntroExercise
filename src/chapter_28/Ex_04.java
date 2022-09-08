package chapter_28;

import java.util.ArrayList;
import java.util.List;

public class Ex_04 {
    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {0, 1}, {0, 2}, {0, 3},
                {1, 0}, {1, 3},
                {2, 0}, {2, 3},
                {3, 0}, {3, 1}, {3, 2},
                {4, 5},
                {5, 4},
        };

        MyGraph<Integer> graph = new MyGraph<>(edges, 6);
        System.out.println(graph.getConnectedComponents());
    }

}
class MyGraph<V> extends UnweightedGraph<V> {
    public MyGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public List<List<Integer>> getConnectedComponents() {
        List<List<Integer>> lists = new ArrayList<>();
        boolean[] isVisited = new boolean[getSize()];

        for (int i = 0; i < getSize(); i++) {
            if (!isVisited[i]) {
                SearchTree tree = dfs(i);
                List<Integer> connectedVertices = tree.getSearchOrder();
                for (int j : connectedVertices) {
                    isVisited[j] = true;
                }
                lists.add(connectedVertices);
            }
        }

        return lists;
    }
}



