package chapter_28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/** Depth-First Search using Stack
 * Input: G = (V, E) and a starting vertex v
 * Output: a DFS tree rooted at v
 *
 * SearchTree dfs(vertex v) {
 *     new stack s;
 *
 *     s push v;
 *     while (s not empty) {
 *         i = s.peek
 *         if (i is not visited) {
 *             visit i;
 *         }
 *
 *         for each neighbor w of i{
 *             if (w is not visited) {
 *                 s push w;
 *                 set v as the parent for w in the tree;
 *                 break;
 *             }
 *         }
 *     }
 * }
 * */
public class Ex_03 {
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

        Graph<String> graph = new UnweightedGraphWithNonrecursiveDFS<>(vertices, edges);
        UnweightedGraph<String>.SearchTree dfs =
                graph.dfs(graph.getIndex("Chicago"));

        java.util.List<Integer> searchOrders = dfs.getSearchOrder();
        System.out.println(dfs.getNumberOfVerticesFound() +
                " vertices are searched in this DFS order:");
        for (Integer searchOrder : searchOrders)
            System.out.print(graph.getVertex(searchOrder) + " ");
        System.out.println();

        for (int i = 0; i < searchOrders.size(); i++)
            if (dfs.getParent(i) != -1)
                System.out.println("parent of " + graph.getVertex(i) +
                        " is " + graph.getVertex(dfs.getParent(i)));
    }
}

class UnweightedGraphWithNonrecursiveDFS<E> extends UnweightedGraph<E> {
    public UnweightedGraphWithNonrecursiveDFS(E[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    @Override
    public Ex25UnweightedGraph<E>.SearchTree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        // Initialize parent[i] to -1
        Arrays.fill(parent, -1);

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        // DFS
        while (!stack.empty()) {
            int i = stack.peek();
            if (!isVisited[i]) {
                searchOrder.add(i);
                isVisited[i] = true;
            }

            for (Edge e : neighbors.get(i)) {
                if (!isVisited[e.v]) {
                    parent[e.v] = i;
                    stack.push(e.v);
                    break;
                }
            }

            // All neighbors are visited. Pop to backtrack.
            if (stack.peek() == i) {
                stack.pop();
            }
        }

        // Return a search tree
        return new SearchTree(v, parent, searchOrder);
    }
}
