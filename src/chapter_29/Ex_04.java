package chapter_29;

import chapter_28.NineTailModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_04 {
    public static void main(String[] args) {
        // Prompt the user to enter nine coins H�s and T's
        System.out.print("Enter an initial nine coin H's and T's: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] initialNode = s.toCharArray();

        Ex04WeightedNineTailModel model = new Ex04WeightedNineTailModel();
        java.util.List<Integer> path =
                model.getShortestPath(NineTailModel.getIndex(initialNode));

        System.out.println("The steps to flip the coins are ");
        for (int i : path)
            NineTailModel.printNode(
                    NineTailModel.getNode(i));

        System.out.println("The number of flips is " +
                model.getNumberOfFlips(NineTailModel.getIndex(initialNode)));
    }
}

class Ex04WeightedNineTailModel extends WeightedNineTailModel {
    /** Construct a model */
    public Ex04WeightedNineTailModel() {
        // Create edges
        List<WeightedEdge> edges = getEdges();

        // Create a graph
        WeightedGraph<Integer> graph = new WeightedGraph<>(
                edges, NUMBER_OF_NODES);

        // Obtain a shortest path tree rooted at the target node
        tree = graph.getShortestPath(511);
    }

    /** Create all edges for the graph */
    private List<WeightedEdge> getEdges() {
        // Store edges
        List<WeightedEdge> edges = new ArrayList<>();

        for (int u = 0; u < NUMBER_OF_NODES; u++) {
            for (int k = 0; k < 9; k++) {
                char[] node = getNode(u); // Get the node for vertex u
                if (node[k] == 'H') {
                    int v = getFlippedNode(node, k);
                    int numberOfFlips = getNumberOfFlips(u, v);

                    // Add edge (v, u) for a legal move from node u to node v
                    edges.add(new WeightedEdge(v, u, numberOfFlips));
                }
            }
        }

        return edges;
    }

    private static int getNumberOfFlips(int u, int v) {
        char[] node1 = getNode(u);
        char[] node2 = getNode(v);

        int count = 0; // Count the number of different cells
        for (int i = 0; i < node1.length; i++)
            if (node1[i] != node2[i]) count++;

        return 3 * count;
    }
}