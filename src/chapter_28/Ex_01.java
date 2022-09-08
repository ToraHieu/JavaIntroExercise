package chapter_28;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Ex_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a URL: ");
        try {
            URL url = new URL(scanner.next());
            Scanner input = new Scanner(url.openStream());
            final int numberOfVertices = input.nextInt();
            input.nextLine();

            UnweightedGraph<Integer> g = new UnweightedGraph<>();
            for (int i = 0; i < numberOfVertices; i++)
                g.addVertex(i);

            while (input.hasNextLine()) {
                String[] arr = input.nextLine().split("\\s+");
                int vertex = Integer.parseInt(arr[0]);
                for (int i = 1; i < arr.length; i++) {
                    int neighbor = Integer.parseInt(arr[i]);
                    g.addEdge(vertex, neighbor);
                }
            }

            g.printEdges();
            UnweightedGraph<Integer>.SearchTree tree = g.dfs(0);
            System.out.print("The graph is " +
                    (tree.getNumberOfVerticesFound() == numberOfVertices ? "" : "not ")
                    + "connected");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
