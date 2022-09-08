package chapter_28;

import java.util.ArrayList;
import java.util.List;

public class KnightTourModel {
    public static final int BOARD_LENGTH = 8;
    private static final int NUMBER_OF_NODES = BOARD_LENGTH * BOARD_LENGTH;
    private final UnweightedGraphWithHamiltonian<Integer> graph; // Define a graph

    public KnightTourModel() {
        List<Edge> edges = getEdges();

        graph = new UnweightedGraphWithHamiltonian<>(edges, NUMBER_OF_NODES);
    }

    public List<Integer> getHamiltonianPath(int v) {
        return graph.getHamiltonianPath(v);
    }

    public List<Integer> getHamiltonianCycle() {
        return graph.getHamiltonianCycle();
    }

    public List<Integer> getHamiltonianCycle(int v) {
        return graph.getHamiltonianCycle(v);
    }

    public static List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                int u = i * BOARD_LENGTH + j;

                // Check eight possible edges from u
                if (i - 1 >= 0 && j - 2 >= 0) {
                    int v1 = (i - 1) * BOARD_LENGTH + (j - 2);
                    edges.add(new Edge(u, v1));
                }

                if (i - 1 >= 0 && j + 2 <= (BOARD_LENGTH - 1)) {
                    int v2 = (i - 1) * BOARD_LENGTH + (j + 2);
                    edges.add(new Edge(u, v2));
                }

                if (i - 2 >= 0 && j - 1 >= 0) {
                    int v3 = (i - 2) * BOARD_LENGTH + (j - 1);
                    edges.add(new Edge(u, v3));
                }

                if (i - 2 >= 0 && j + 1 <= (BOARD_LENGTH - 1)) {
                    int v4 = (i - 2) * BOARD_LENGTH + (j + 1);
                    edges.add(new Edge(u, v4));
                }

                if (i + 1 <= (BOARD_LENGTH - 1) && j - 2 >= 0) {
                    int v5 = (i + 1) * BOARD_LENGTH + (j - 2);
                    edges.add(new Edge(u, v5));
                }

                if (i + 1 <= (BOARD_LENGTH - 1) && j + 2 <= (BOARD_LENGTH - 1)) {
                    int v6 = (i + 1) * BOARD_LENGTH + (j + 2);
                    edges.add(new Edge(u, v6));
                }

                if (i + 2 <= (BOARD_LENGTH - 1) && j - 1 >= 0) {
                    int v7 = (i + 2) * BOARD_LENGTH + (j - 1);
                    edges.add(new Edge(u, v7));
                }

                if (i + 2 <= (BOARD_LENGTH - 1) && j + 1 <= (BOARD_LENGTH - 1)) {
                    int v8 = (i + 2) * BOARD_LENGTH + (j + 1);
                    edges.add(new Edge(u, v8));
                }
            }
        }

        return edges;
    }
}
