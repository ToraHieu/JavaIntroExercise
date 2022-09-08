package chapter_29;

import chapter_28.Displayable;
import chapter_28.Edge;
import chapter_28.UnweightedGraph;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex_17 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ex17WeightedGraph<Ex17GraphView.Vertex> graph = new Ex17WeightedGraph<>();
        Ex17GraphView view = new Ex17GraphView(graph);

        Button mstBtn = new Button("MST");
        HBox mstBox = new HBox(mstBtn);
        mstBox.setStyle("-fx-border-color: BLACK");
        mstBox.setPadding(new Insets(5));
        Label mstLbl = new Label("Display MST", mstBox);
        mstLbl.setContentDisplay(ContentDisplay.BOTTOM);

        TextField sptTf = new TextField();
        sptTf.setPrefColumnCount(3);
        Label sptTfLbl = new Label("Start Vertex:", sptTf);
        sptTfLbl.setContentDisplay(ContentDisplay.RIGHT);
        Button sptBtn = new Button("Shortest Path Tree");
        HBox sptBox = new HBox(sptTfLbl, sptBtn);
        sptBox.setStyle("-fx-border-color: BLACK");
        sptBox.setPadding(new Insets(5));
        sptBox.setSpacing(2);
        Label sptLbl = new Label("Display SP Tree", sptBox);
        sptLbl.setContentDisplay(ContentDisplay.BOTTOM);

        HBox trees = new HBox(mstLbl, sptLbl);
        trees.setSpacing(2);
        trees.setAlignment(Pos.CENTER);

        TextField aspStartTf = new TextField();
        aspStartTf.setPrefColumnCount(3);
        Label aspStartTfLbl = new Label("Start Vertex:", aspStartTf);
        aspStartTfLbl.setContentDisplay(ContentDisplay.RIGHT);
        TextField aspEndTf = new TextField();
        aspEndTf.setPrefColumnCount(3);
        Label aspEndTfLbl = new Label("End Vertex:", aspEndTf);
        aspEndTfLbl.setContentDisplay(ContentDisplay.RIGHT);
        Button aspBtn = new Button("Shortest Path");
        HBox aspBox = new HBox(aspStartTfLbl, aspEndTfLbl, aspBtn);
        aspBox.setStyle("-fx-border-color: BLACK");
        aspBox.setPadding(new Insets(5));
        aspBox.setSpacing(2);
        Label aspLbl = new Label("Display SP Tree", aspBox);
        aspLbl.setContentDisplay(ContentDisplay.BOTTOM);

        Button tspBtn = new Button("Solve it");
        HBox tspBox = new HBox(tspBtn);
        tspBox.setStyle("-fx-border-color: BLACK");
        tspBox.setPadding(new Insets(5));
        Label tspLbl = new Label("Travelling Salesman Problem", tspBox);
        tspLbl.setContentDisplay(ContentDisplay.BOTTOM);

        VBox vBox = new VBox(5, trees, aspLbl, tspLbl);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane(view);
        pane.setBottom(vBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 750, 600);
        primaryStage.setTitle("Ex_17"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        mstBox.setMinWidth(mstLbl.getWidth() + 10);
        tspBox.setMinWidth(tspLbl.getWidth() + 10);

        mstBtn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                view.setTree(graph.getMinimumSpanningTree());
            }
        });

        sptBtn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int i = Integer.parseInt(sptTf.getText());
                view.setTree(graph.getShortestPath(i));
            }
        });

        aspBtn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int start = Integer.parseInt(aspStartTf.getText());
                int end = Integer.parseInt(aspEndTf.getText());
                view.highlightPath(graph.getPathIndex(graph.getShortestPath(start).getPath(end)));
            }
        });

        tspBtn.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                List<Integer> hamiltonianCycle = graph.getHamiltonianCycle();
                if (hamiltonianCycle != null) {
                    hamiltonianCycle.add(hamiltonianCycle.get(0));
                    view.highlightPath(hamiltonianCycle);
                }
            }
        });
    }
}

class Ex17GraphView extends WeightedGraphView {
    private static final int RADIUS = 20;
    private final Line connectingLine = new Line();
    private final Ex17WeightedGraph<Vertex> graph;
    private UnweightedGraph<Vertex>.SearchTree tree;
    private final ArrayList<Node> highlightNodes = new ArrayList<>();

    public Ex17GraphView(Ex17WeightedGraph<Vertex> graph) {
        super(graph);
        this.graph = graph;

        // Detecting mouse click in/near any vertex
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.isStillSincePress()) {
                double detectionRange = RADIUS << 2;
                for (Vertex v : graph.getVertices()) {
                    // distance = Math.hypot(x1-x2, y1-y2)
                    if (Math.hypot(event.getX() - v.getX(), event.getY() - v.getY()) <= detectionRange) {
                        // Distance is inside detection range
                        return;
                    }
                }
                // Event is not near any of the existing vertices, add a new vertex on Primary click
                graph.addVertex(new Vertex((int) event.getX(), (int) event.getY(),
                        String.valueOf(graph.getSize())));
                reDraw();
            }
        });
    }

    @Override
    protected void drawVertices() {
        if (graph == null) return;
        for (int i = 0; i < graph.getSize(); i++) {
            Vertex v = graph.getVertices().get(i);
            int x = v.getX();
            int y = v.getY();
            String name = String.valueOf(i); // Name is the index
            Circle circle = new Circle(x, y, RADIUS);
            circle.setFill(Paint.valueOf("white"));
            circle.setStroke(Paint.valueOf("black"));
            Text text = new Text(name);
            text.setDisable(true);
            StackPane stackPane = new StackPane(circle, text);
            stackPane.setLayoutX(x - RADIUS);
            stackPane.setLayoutY(y - RADIUS);

            getChildren().add(stackPane); // Display a vertex
            circle.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    graph.remove(v);
                    reDraw();
                }
            });

            circle.setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    connectingLine.setStartX(event.getX());
                    connectingLine.setStartY(event.getY());
                    connectingLine.setEndX(event.getX());
                    connectingLine.setEndY(event.getY());
                    getChildren().add(connectingLine);
                }
            });

            circle.setOnMouseDragged(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    connectingLine.setEndX(event.getX());
                    connectingLine.setEndY(event.getY());
                }
            });

            int finalI = i;
            circle.setOnMouseReleased(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    getChildren().remove(connectingLine);
                    for (int k = 0; k < graph.getVertices().size(); k++) {
                        if (k == finalI) continue;
                        // distance = Math.hypot(x1-x2, y1-y2)
                        if (Math.hypot(event.getX() - graph.getVertices().get(k).getX(),
                                event.getY() - graph.getVertices().get(k).getY()) <= RADIUS) {
                            // Distance is inside detection range
                            double weight = Math.hypot(
                                    circle.getCenterX() - graph.getVertices().get(k).getX(),
                                    circle.getCenterY() - graph.getVertices().get(k).getY());
                            graph.addEdge(finalI, k, weight);
                            graph.addEdge(k, finalI, weight);
                            reDraw();
                            break;
                        }
                    }
                }
            });

            circle.setOnMouseMoved(event -> {
                if (event.isControlDown()) {
                    v.setX((int) event.getX());
                    v.setY((int) event.getY());
                    for (Edge e: graph.getEdges(finalI)) {
                        WeightedEdge weightedEdge = (WeightedEdge) e;
                        double weight = Math.hypot(
                                v.x - graph.getVertices().get(e.v).x,
                                v.y - graph.getVertices().get(e.v).y);
                        weightedEdge.weight = weight;
                        ((WeightedEdge)graph.getEdge(e.v, e.u)).weight = weight;
                    }
                    reDraw();
                }
            });
        }
    }

    public void setTree(UnweightedGraph<Vertex>.SearchTree tree) {
        this.tree = tree;
        highlightSearchTree();
    }

    private void highlightSearchTree() {
        clearHighlight();
        // Draw edges for pairs of vertices
        for (int i = 0; i < graph.getSize(); i++) {
            int parent = tree.getParent(i);
            if (parent != -1) {
                int x1 = graph.getVertex(parent).getX();
                int y1 = graph.getVertex(parent).getY();

                int x2 = graph.getVertex(i).getX();
                int y2 = graph.getVertex(i).getY();

                Line line = new Line(x1,y1, x2,y2);
                line.setStyle("-fx-stroke: red");
                double[] arrowCoors = getArrowHeadCoor(x1, y1, x2, y2, 10);
                Line arrowWingUp = new Line(x2, y2, arrowCoors[0], arrowCoors[1]);
                arrowWingUp.setStyle("-fx-stroke: red");

                Line arrowWingDown = new Line(x2, y2, arrowCoors[2], arrowCoors[3]);
                arrowWingDown.setStyle("-fx-stroke: red");

                highlightNodes.add(line);
                highlightNodes.add(arrowWingUp);
                highlightNodes.add(arrowWingDown);
                getChildren().addAll(line, arrowWingUp, arrowWingDown);
                line.toFront();
                arrowWingUp.toFront();
                arrowWingDown.toFront();
            }
        }
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

    /** Return coordinates of 2 points that form an arrow head at x2, y2
     * @return  an array contains x1, y1, x2, y2 of 2 points*/
    @SuppressWarnings("SameParameterValue")
    private double[] getArrowHeadCoor(double x1, double y1, double x2, double y2, double size) {
        double[] coor = new double[4];
        double dx = x2 - x1;
        double dy = y2 - y1;

        double length = Math.sqrt(dx * dx + dy * dy);
        double udx = dx / length;
        double udy = dy / length;

        coor[0] = x2 - udx * size - udy * size;
        coor[1] = y2 - udy * size + udx * size;
        coor[2] = x2 - udx * size + udy * size;
        coor[3] = y2 - udy * size - udx * size;
        return coor;
    }

    static class Vertex implements Displayable {
        private int x, y;
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

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}

class Ex17WeightedGraph<V> extends WeightedGraph<V> {
    public List<Integer> getPathIndex(List<V> vs) {
        List<Integer> path = new ArrayList<>();
        for (V v: vs) {
            for (int i = 0; i < vertices.size(); i++) {
                if (v == vertices.get(i)){
                    path.add(i);
                    break;
                }
            }
        }

        return path;
    }

    /** Return a shortest cycle
     * Return null if no such cycle exists */
    public List<Integer> getShortestHamiltonianCycle() {
        return getHamiltonianCycle();
    }

    /** Return a Hamiltonian cycle
     * Return null if the graph doesn't contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle() {
        return getHamiltonianPathOrCycle(0, true);
    }

    /** Helper method for custom search Hamiltonian path or cycle */
    @SuppressWarnings("SameParameterValue")
    private List<Integer> getHamiltonianPathOrCycle(int index, boolean isSearchingForCycle) {
        /* A path starts from v. (i, next[i]) represents an edge in the path.
        isVisited[i] tracks whether i is currently in the path. */
        int[] next = new int[getSize()];
        // Indicate no subpath from i is found yet
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];

        // The vertices in the Hamiltonian path are stored in result
        List<Integer> result = null;

        /* To speed up search, reorder the adjacency list for each vertex
        so that the vertices in the list are in increasing order of their degrees */
        for (int i = 0; i < getSize(); i++) {
            reorderNeighborsOnWeight(neighbors.get(i));
        }

        if (getHamiltonianPathOrCycle(index, index, next, isVisited, isSearchingForCycle)) {
            result = new ArrayList<>();
            int vertex = index;
            while (vertex != -1) {
                result.add(vertex);
                vertex = next[vertex];
            }
        }

        return result;
    }

    /**
     * Search for a Hamiltonian path from v
     */
    private boolean getHamiltonianPathOrCycle(int root, int v, int[] next, boolean[] isVisited,
                                              boolean isSearchingForCycle) {
        isVisited[v] = true;

        if (allVisited(isVisited)) {
            if (!isSearchingForCycle)
                return true; // The path now includes all vertices, thus is found
            else {
                for (Edge e: neighbors.get(v)) {
                    if (e.v == root)
                        return true; // The last vertex connect with the root, thus a cycle is found
                }
                isVisited[v] = false; // Back track
                return false;
            }
        }

        for (int i = 0; i < neighbors.get(v).size(); i++) {
            int u = neighbors.get(v).get(i).v;
            if (!isVisited[u] && getHamiltonianPathOrCycle(root, u, next, isVisited, isSearchingForCycle)) {
                next[v] = u;
                return true;
            }
        }

        isVisited[v] = false;
        return false;
    }

    /**
     * Return true if all elements in array isVisited are true
     */
    private boolean allVisited(boolean[] isVisited) {
        for (int i = 0; i < getSize(); i++) {
            if (!isVisited[i])
                return false;
        }

        return true;
    }

    /**
     * Reorder the adjacency list in increasing order of degrees
     */
    private void reorderNeighborsOnWeight(List<Edge> list) {
        for (int i = list.size() - 1; i >= 1; i--) {
            // Find the maximum in the list[0..i]
            double currentMaxWeight = ((WeightedEdge)list.get(0)).weight;
            int currentMaxIndex = 0;

            for (int j = 1; j <= i; j++) {
                if (currentMaxWeight < getDegree(list.get(j).v)) {
                    currentMaxWeight = getDegree(list.get(j).v);
                    currentMaxIndex = j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary
            if (currentMaxIndex != i) {
                Edge temp = list.get(currentMaxIndex);
                list.set(currentMaxIndex, list.get(i));
                list.set(i, temp);
            }
        }
    }

    public List<Edge> getEdges(int i) {
        return neighbors.get(i);
    }

    public Edge getEdge(int u, int v) {
        if (u < getSize() && v < getSize()) {
            for (int i = 0; i < getNeighbors(u).size(); i++) {
                if (getNeighbors(u).get(i) == v) {
                    return neighbors.get(u).get(i);
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean remove(V v) {
        if (!vertices.contains(v))
            return false;

        int i = vertices.indexOf(v);
        vertices.remove(i);
        neighbors.remove(i);

        for (java.util.List<Edge> neighbor : neighbors) {
            for (int k = 0; k < neighbor.size(); k++) {
                if (neighbor.get(k).u > i)
                    neighbor.get(k).u--; // Shift left
                if (neighbor.get(k).v == i) {
                    neighbor.remove(k--); //  Remove Edge with v's index
                } else if (neighbor.get(k).v > i) {
                    neighbor.get(k).v--; // Shift left
                }
            }
        }

        return true;
    }

    @Override
    public boolean remove(int u, int v) {
        if (u <= neighbors.size()) {
            for (Edge e : neighbors.get(u)) {
                if (e.v == v) {
                    neighbors.get(u).remove(e);
                    return true;
                }
            }
        }

        return false;
    }
}