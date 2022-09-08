package chapter_28;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ex_22 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(new Ex_22.CirclePane(), 450, 350);
        primaryStage.setTitle("ConnectedCircles"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Panel for displaying circles */
    class CirclePane extends Pane {
        public CirclePane() {
            this.setOnMouseClicked(e -> {
                if (!isInsideACircle(new Point2D(e.getX(), e.getY()))) {
                    // Add a new circle
                    Circle circle = new Circle(e.getX(), e.getY(), 20);
                    enableDrag(circle);
                    circle.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> colorIfConnected());
                    getChildren().add(circle);
                    colorIfConnected();
                }
            });
        }

        /** Returns true if the point is inside an existing circle */
        private boolean isInsideACircle(Point2D p) {
            for (Node circle: this.getChildren())
                if (circle.contains(p))
                    return true;

            return false;
        }

        /** Color all circles if they are connected */
        private void colorIfConnected() {
            if (getChildren().size() == 0)
                return; // No circles in the pane

            // Build the edges
            java.util.List<Edge> edges =
                    new java.util.ArrayList<>();
            for (int i = 0; i < getChildren().size(); i++)
                for (int j = i + 1; j < getChildren().size(); j++)
                    if (overlaps((Circle)(getChildren().get(i)),
                            (Circle)(getChildren().get(j)))) {
                        edges.add(new Edge(i, j));
                        edges.add(new Edge(j, i));
                    }

            // Create a graph with circles as vertices
            Graph<Node> graph = new UnweightedGraph<>
                    ((java.util.List<Node>)getChildren(), edges);
            UnweightedGraph<Node>.SearchTree tree = graph.dfs(0);
            boolean isAllCirclesConnected = getChildren().size() == tree
                    .getNumberOfVerticesFound();

            for (Node circle: getChildren()) {
                if (isAllCirclesConnected) { // All circles are connected
                    ((Circle)circle).setFill(Color.RED);
                }
                else {
                    ((Circle)circle).setStroke(Color.BLACK);
                    ((Circle)circle).setFill(Color.WHITE);
                }
            }
        }
    }

    public static boolean overlaps(Circle circle1, Circle circle2) {
        return new Point2D(circle1.getCenterX(), circle1.getCenterY()).
                distance(circle2.getCenterX(), circle2.getCenterY())
                <= circle1.getRadius() + circle2.getRadius();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    static class Delta { double x, y; }
    // make a node movable by dragging it around with the mouse.
    private void enableDrag(final Circle circle) {
        final Delta dragDelta = new Delta();
        circle.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = circle.getCenterX() - mouseEvent.getX();
            dragDelta.y = circle.getCenterY() - mouseEvent.getY();
            circle.getScene().setCursor(Cursor.MOVE);
        });
        circle.setOnMouseReleased(mouseEvent -> circle.getScene().setCursor(Cursor.HAND));
        circle.setOnMouseDragged(mouseEvent -> {
            circle.setCenterX(mouseEvent.getX() + dragDelta.x);
            circle.setCenterY(mouseEvent.getY() + dragDelta.y);
        });
        circle.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                circle.getScene().setCursor(Cursor.HAND);
            }
        });
        circle.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                circle.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }
}
