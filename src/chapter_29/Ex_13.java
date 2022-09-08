package chapter_29;

import chapter_28.Displayable;
import chapter_28.Graph;
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

public class Ex_13 extends Application {
    private static final String
            INSTRUCTION_TEXT = "Enter a city name",
            ERROR_TEXT = "Invalid city name entered";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
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

        // Edge array for graph in Figure 29.1
        int[][] edges = {
                {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
                {6, 5, 983}, {6, 7, 214},
                {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
                {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
                {9, 8, 661}, {9, 11, 1187},
                {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
                {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };

        WeightedGraph<City> graph = new WeightedGraph<>(vertices, edges);
        Ex13GraphView view = new Ex13GraphView(graph);

        Text status = new Text(INSTRUCTION_TEXT);

        TextField startTf = new TextField(), endTf = new TextField();
        Button dspBtn = new Button("Display Shorted Path");
        HBox bottom = new HBox(new Label("Starting City:"), startTf,
                new Label("Ending City:"), endTf, dspBtn);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(5);

        BorderPane pane = new BorderPane(view);
        pane.setTop(status);
        BorderPane.setAlignment(status, Pos.CENTER);
        pane.setBottom(bottom);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 750, 470);
        primaryStage.setTitle("DisplayUSMap"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        dspBtn.setOnMouseClicked(searchHandler(vertices, graph, view, status, startTf, endTf));
    }

    @NotNull
    private EventHandler<MouseEvent> searchHandler(City[] vertices, WeightedGraph<City> graph,
                                                   Ex13GraphView view, Text status,
                                                   TextField startTf, TextField endTf) {
        return event -> {
            int startIndex = getCityIndex(startTf.getText().trim(), vertices);
            int endIndex = getCityIndex(endTf.getText().trim(), vertices);
            if (startIndex != -1 && endIndex != -1) {
                List<? extends Displayable> verticesPath =
                        graph.getShortestPath(startIndex).getPath(endIndex);
                view.highlightPath(getPathIndex(verticesPath, vertices));
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

    private List<Integer> getPathIndex(List<? extends Displayable> list, City[] cities) {
        List<Integer> path = new ArrayList<>();
        for (Displayable d: list) {
            path.add(getCityIndex(d.getName(), cities));
        }

        return path;
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
}

class Ex13GraphView extends WeightedGraphView {
    private final Graph<? extends Displayable> graph;
    private final ArrayList<Node> highlightNodes = new ArrayList<>();

    public Ex13GraphView(Graph<? extends Displayable> graph) throws Exception {
        super(graph);
        this.graph = graph;
    }

    public void highlightPath(List<Integer> path) {
        clearHighlight();
        for (int i = 1; i < path.size(); i++) {
            int x1 = graph.getVertex(path.get(i)).getX();
            int y1 = graph.getVertex(path.get(i)).getY();

            int x2 = graph.getVertex(path.get(i -1)).getX();
            int y2 = graph.getVertex(path.get(i -1)).getY();

            addHighlightedLine(x1, y1, x2, y2);
        }
    }

    private void addHighlightedLine(int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStyle("-fx-stroke: red");

        highlightNodes.add(line);
        getChildren().add(line);
        line.toFront();
    }

    private void clearHighlight() {
        for (Node node: highlightNodes) {
            getChildren().remove(node);
        }
        highlightNodes.clear();
    }
}