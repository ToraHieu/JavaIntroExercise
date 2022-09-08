package chapter_28;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Ex_19 extends Application {
    private static final String
            INSTRUCTION_TEXT = "Enter a city name",
            ERROR_TEXT = "Invalid city name entered";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        City[] vertices = {
                new City("Seattle", 75, 50),
                new City("San Francisco", 50, 210),
                new City("Los Angeles", 75, 275),
                new City("Denver", 275, 175),
                new City("Kansas City", 400, 245),
                new City("Chicago", 450, 100),
                new City("Boston", 700, 80),
                new City("New York", 675, 120),
                new City("Atlanta", 575, 295),
                new City("Miami", 600, 400),
                new City("Dallas", 408, 325),
                new City("Houston", 450, 360) };

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

        Graph<City> graph = new UnweightedGraph<>(vertices, edges);
        GraphViewWithTree view = new GraphViewWithTree(graph);

        Text status = new Text(INSTRUCTION_TEXT);

        TextField cityTf = new TextField();
        Button dfs = new Button("DFS"), bfs = new Button("BFS");
        HBox bottom = new HBox(new Label("Enter a city:"), cityTf, dfs, bfs);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(5);

        BorderPane pane = new BorderPane(view);
        pane.setTop(status);
        BorderPane.setAlignment(status, Pos.CENTER);
        pane.setBottom(bottom);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 750, 450);
        primaryStage.setTitle("DisplayUSMap"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        dfs.setOnMouseClicked(searchHandler(vertices, graph, view, status, cityTf, true));

        bfs.setOnMouseClicked(searchHandler(vertices, graph, view, status, cityTf, false));
    }

    @NotNull
    private EventHandler<MouseEvent> searchHandler(City[] vertices, Graph<City> graph,
                                                   GraphViewWithTree view, Text status, TextField cityTf,
                                                   boolean isDFS) {
        return event -> {
            int index = getCityIndex(cityTf.getText().trim(), vertices);
            if (index != -1) {
                view.setTree(isDFS ? graph.dfs(index) : graph.bfs(index));
                status.setText(INSTRUCTION_TEXT);
            } else
                status.setText(ERROR_TEXT);
        };
    }

    private int getCityIndex(String name, City[] cities) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].name.equals(name))
                return i;
        }
        return -1;
    }

    static class City implements Displayable {
        private final int x, y;
        private final String name;

        City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class GraphViewWithTree extends GraphView {
    private final Graph<? extends Displayable> graph;
    private UnweightedGraph<? extends Displayable>.SearchTree tree;
    private final ArrayList<Node> highlightNodes = new ArrayList<>();

    public GraphViewWithTree(Graph<? extends Displayable> graph) {
        super(graph);
        this.graph = graph;
    }

    public void setTree(UnweightedGraph<? extends Displayable>.SearchTree tree) {
        this.tree = tree;
        highlightSearchTree();
    }

    private void highlightSearchTree() {
        clearHighlight();
        List<Integer> searchOrder = tree.getSearchOrder();
        // Draw edges for pairs of vertices
        for (int i = 0; i < searchOrder.size(); i++) {
            int parent = tree.getParent(i);
            if (parent != -1) {
                int x1 = graph.getVertex(i).getX();
                int y1 = graph.getVertex(i).getY();

                int x2 = graph.getVertex(parent).getX();
                int y2 = graph.getVertex(parent).getY();

                Line line = new Line(x1,y1, x2,y2);
                line.setStyle("-fx-stroke: red");

                highlightNodes.add(line);
                getChildren().add(line);
                line.toFront();
            }
        }
    }

    private void clearHighlight() {
        for (Node node: highlightNodes) {
            getChildren().remove(node);
        }
        highlightNodes.clear();
    }
}
