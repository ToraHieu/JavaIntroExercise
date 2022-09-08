package chapter_28;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex_20 extends Application {
    @Override
    public void start(Stage primaryStage) {
        UnweightedGraph<Vertex> graph = new UnweightedGraph<>();

        try (Scanner input = new Scanner(new File("src/chapter_28/Ex_20-graph1.txt"))) {
            final int numberOfVertices = input.nextInt();
            input.nextLine();

            for (int i = 0; i < numberOfVertices; i++)
                graph.addVertex(new Vertex(i + ""));

            while (input.hasNextLine()) {
                String[] arr = input.nextLine().split("\\s+");
                int vertex = Integer.parseInt(arr[0]);
                graph.getVertex(vertex).setX(Integer.parseInt(arr[1]));
                graph.getVertex(vertex).setY(Integer.parseInt(arr[2]));

                for (int i = 3; i < arr.length; i++) {
                    int neighbor = Integer.parseInt(arr[i]);
                    graph.addEdge(vertex, neighbor);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create a scene and place it in the stage
        Scene scene = new Scene(new GraphView(graph), 200, 200);
        primaryStage.setTitle("Ex_20"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    static class Vertex implements Displayable {
        private int x, y;
        private String name;

        public Vertex(String name) {
            this(0, 0, name);
        }

        public Vertex(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
