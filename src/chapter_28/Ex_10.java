package chapter_28;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ex_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: "); // src/chapter_28/GraphSample1.txt
        try {
            File file = new File(scanner.next());
            if (!file.exists()) {
                System.out.print("File not found.");
                System.exit(1001);
            }
            Scanner input = new Scanner(file);
            final int numberOfVertices = input.nextInt();
            input.nextLine();

            System.out.print("Enter two vertices (integer indexes): ");
            int start = scanner.nextInt(), end = scanner.nextInt();
            if (start < 0 || start >= numberOfVertices || end < 0 || end >= numberOfVertices) {
                throw new IndexOutOfBoundsException();
            }

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

            System.out.println("The number of vertices is " + g.getSize());
            g.printEdges();

            UnweightedGraph<Integer>.SearchTree tree = g.bfs(start);
            List<Integer> path = tree.getPath(end);
            StringBuilder result = new StringBuilder("The path is");
            for (int i = path.size() - 1; i >= 0; i--)
                result.append(" ").append(path.get(i));
            System.out.print(result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
