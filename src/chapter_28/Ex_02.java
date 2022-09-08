package chapter_28;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Ex_02 {
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

        Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);

        File output = new File("src/chapter_28/Ex_02-graph1.txt");
        try {
            PrintWriter writer = new PrintWriter(output);
            final int numberOfVertices = graph1.getSize();
            writer.println(numberOfVertices);
            for (String vertex: graph1.getVertices()) {
                int vertexIndex = graph1.getIndex(vertex);
                writer.print(vertexIndex);
                for (int neighbor: graph1.getNeighbors(vertexIndex)) {
                    writer.print(" " + neighbor);
                }
                writer.println();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
