package chapter_28;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_05 {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston"};

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

        UnweightedGraphWithGetPath<String> graph = new UnweightedGraphWithGetPath<>(vertices, edges);

        final String start, end;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a starting city: ");
            start = scanner.next();
            System.out.print("Enter an ending city: ");
            end = scanner.next();
        }
        System.out.print("The path is");
        List<Integer> list = graph.getPath(graph.getIndex(start), graph.getIndex(end));
        for (int cityIndex: list) {
            System.out.print(" " + graph.getVertex(cityIndex));
        }
    }
}

class UnweightedGraphWithGetPath<V> extends UnweightedGraph<V> {
    public UnweightedGraphWithGetPath(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    /** Return a path of vertices from u vertex to v.
     * If there's no path, return null.*/
    public List<Integer> getPath(int u, int v) {
        SearchTree tree = bfs(v);
        if (!tree.getSearchOrder().contains(u))
            return null;

        List<Integer> path = new ArrayList<>();
        for (V vertex : tree.getPath(u)) {
            path.add(getIndex(vertex));
        }

        return path;
    }
}