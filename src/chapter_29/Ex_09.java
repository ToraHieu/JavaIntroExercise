package chapter_29;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_09 {
    public static void main(String[] args) {
        System.out.print("Enter a URL: ");
        String url;
        try (Scanner scanner = new Scanner(System.in)) {
            url = scanner.next();
        }
        WeightedGraph<Integer> g;
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
        if (n != -1) {
            g = new WeightedGraph<>(list, n);
            System.out.println("The number of vertices  is " + n);
            g.printWeightedEdges();
            WeightedGraph<Integer>.MST tree = g.getMinimumSpanningTree();
            System.out.println("Total weight in MST is: " + tree.getTotalWeight());
            tree.printTree();
        }
    }
}
