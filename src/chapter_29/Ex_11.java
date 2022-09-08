package chapter_29;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_11 {
    public static void main(String[] args) {
        String url;
        int v1 = -1, v2 = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a URL: ");
            url = scanner.next();
            System.out.print("Enter two vertices (integer indexes): ");
            v1 = scanner.nextInt();
            v2 = scanner.nextInt();
        }
        int n = -1;

        List<WeightedEdge> list = new ArrayList<>();
        try (Scanner input = new Scanner(new URL(url).openStream())) {
            n = input.nextInt();
            input.nextLine();
            for (int i = 0; i < n && input.hasNext(); i++) {
                String line = input.nextLine();
                String[] triplets = line.split("[|]");
                for (String triplet : triplets) {
                    String[] edge = triplet.split("[,]");
                    int u = Integer.parseInt(edge[0].trim());
                    int v = Integer.parseInt(edge[1].trim());
                    double weight = Double.parseDouble(edge[2].trim());
                    list.add(new WeightedEdge(u, v, weight));
                    list.add(new WeightedEdge(v, u, weight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (n != -1 && v1 != -1 && v2 != -1) {
            WeightedGraph<Integer> g = new WeightedGraph<>(list, n);
            System.out.println("The number of vertices  is " + n);
            g.printWeightedEdges();
            WeightedGraph<Integer>.ShortestPathTree tree = g.getShortestPath(v1);
            System.out.print("A path from " + v1 + " to " + v2 +":");
            List<Integer> path = tree.getPath(v2);
            for (int i = path.size() -1; i >= 0; i--) {
                System.out.print(" " + path.get(i));
            }
        }
    }

}
