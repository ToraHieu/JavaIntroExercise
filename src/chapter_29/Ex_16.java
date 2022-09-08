package chapter_29;

import chapter_28.Displayable;
import helpers.IntegerFilter;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Ex_16 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        WeightedGraph<Vertex> graph = new WeightedGraph<>();

        Ex14GraphView view = new Ex14GraphView(graph);
        view.setCircleRadius(8);
        view.setMinSize(400, 200);

        TextField vertexName = new TextField("0"),
                xCoorTf = new TextField(),
                yCoorTf = new TextField(),
                uTf = new TextField(),
                vTf = new TextField(),
                weightTf = new TextField();
        vertexName.setEditable(false);

        xCoorTf.setTextFormatter(getNewFormatter());
        xCoorTf.setText("");
        yCoorTf.setTextFormatter(getNewFormatter());
        yCoorTf.setText("");
        uTf.setTextFormatter(getNewFormatter());
        uTf.setText("");
        vTf.setTextFormatter(getNewFormatter());
        vTf.setText("");
        weightTf.setTextFormatter(getNewFormatter());
        weightTf.setText("");

        Button addVertexBtn = new Button("Add Vertex"),
                addEdgeBtn = new Button("Add Edge"),
                startOverBtn = new Button("Start Over (Clear Graphs)");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(new Text("Add a new vertex"), 0, 0, 2, 1);
        gridPane.addColumn(0, new Text("Vertex name:"),
                new Text("x-coordinate:"), new Text("y-coordinate:"));
        gridPane.addColumn(1, vertexName, xCoorTf, yCoorTf, addVertexBtn);

        gridPane.add(new Text("Add a new edge"), 2, 0, 2, 1);
        gridPane.addColumn(2, new Text("Vertex u (index):"),
                new Text("Vertex v (index):"), new Text("Weight (int):"));
        gridPane.addColumn(3, uTf, vTf, weightTf, addEdgeBtn);

        gridPane.add(startOverBtn, 0, 5, 6, 1);
        GridPane.setHalignment(startOverBtn, HPos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(view);
        pane.setCenter(gridPane);
        BorderPane.setMargin(gridPane, new Insets(5));

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Ex_15"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        addVertexBtn.setOnMouseClicked(event -> {
            int x = Integer.parseInt(xCoorTf.getText());
            int y = Integer.parseInt(yCoorTf.getText());
            graph.addVertex(new Vertex(x, y, vertexName.getText()));
            vertexName.setText(String.valueOf(Integer.parseInt(vertexName.getText()) + 1));
            view.reDraw();
        });

        addEdgeBtn.setOnMouseClicked(event -> {
            int u = Integer.parseInt(uTf.getText());
            int v = Integer.parseInt(vTf.getText());
            if (u < graph.getSize() && v < graph.getSize()) {
                int weight = Integer.parseInt(weightTf.getText());
                graph.addEdge(u, v, weight);
                graph.addEdge(v, u, weight);
                view.reDraw();
                view.setTree(graph.getMinimumSpanningTree());
            }
        });

        startOverBtn.setOnMouseClicked(event -> {
            graph.clear();
            vertexName.setText("0");
            view.reDraw();
        });
    }

    private int getVertexIndex(String name, List<Vertex> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).name.equals(name))
                return i;
        }
        return -1;
    }

    private List<Integer> getPathIndex(List<? extends Displayable> list, List<Vertex> vertices) {
        List<Integer> path = new ArrayList<>();
        for (Displayable d: list) {
            path.add(getVertexIndex(d.getName(), vertices));
        }

        return path;
    }

    @SuppressWarnings("SameParameterValue")
    @NotNull
    private TextFormatter<Integer> getNewFormatter(int defaultValue) {
        return new TextFormatter<>(new IntegerStringConverter(),
                defaultValue, new IntegerFilter());
    }

    private TextFormatter<Integer> getNewFormatter() {
        return getNewFormatter(0);
    }


        static class Vertex implements Displayable {
        private final int x, y;
        private final String name;

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

        @Override
        public String getName() {
            return name;
        }
    }
}
