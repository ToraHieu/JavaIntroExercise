package chapter_29;

import chapter_28.Displayable;
import chapter_28.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

class WeightedGraphView extends Pane {
    private double circleRadius = 16;
    private final Graph<? extends Displayable> graph;
    private final int offset = 2;

    public WeightedGraphView(Graph<? extends Displayable> graph) {
        this.graph = graph;
        reDraw();
    }

    public void reDraw() {
        getChildren().clear();
        drawVertices();
        drawEdges();
    }

    protected void drawVertices() {
        // Draw vertices
        java.util.List<? extends Displayable> vertices
                = graph.getVertices();
        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().add(new Circle(x, y, circleRadius)); // Display a vertex
            getChildren().add(new Text(x - circleRadius/2 - (offset<<2), y - circleRadius - offset, name));
        }
    }

    protected void drawEdges() {
        // Draw edges for pairs of vertices
        for (int i = 0; i < graph.getSize(); i++) {
            java.util.List<Integer> neighbors = graph.getNeighbors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v : neighbors) {
                if (v > i) continue;
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();
                Line line = new Line(x1, y1, x2, y2);
                // Draw an edge for (i, v)
                getChildren().add(line);
                line.toBack();
                Text text = null;
                try {
                    //noinspection unchecked
                    text = new Text(String.format("%.2f",
                            ((WeightedGraph<? extends Displayable>) graph).getWeight(i, v)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                double x3 = (x1 + x2) >> 1;
                double y3 = (y1 + y2) >> 1;
                assert text != null;
                text.setX(x3 - 10);
                text.setY(y3 - 5);
                getChildren().add(text);
            }
        }
    }

    public void setCircleRadius(double circleRadius) {
        this.circleRadius = circleRadius;
        try {
            reDraw();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
