package chapter_28;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ex_23 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(new RectanglePane(), 450, 350);
        primaryStage.setTitle("ConnectedRectangles"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Panel for displaying rectangles */
    @SuppressWarnings("InnerClassMayBeStatic")
    class RectanglePane extends Pane {
        private final double width = 40, height = 40;
        
        public RectanglePane() {
            this.setOnMouseClicked(e -> {
                if (!isInsideARectangle(new Point2D(e.getX(), e.getY()))) {
                    // Add a new rectangle
                    getChildren().add(
                            new Rectangle(e.getX() - width/2, e.getY()  - height/2,
                                    width, height));
                    colorIfConnected();
                }
            });
        }

        /** Returns true if the point is inside an existing rectangle */
        private boolean isInsideARectangle(Point2D p) {
            for (Node rectangle: this.getChildren())
                if (rectangle.contains(p))
                    return true;

            return false;
        }

        /** Color all rectangles if they are connected */
        private void colorIfConnected() {
            if (getChildren().size() == 0)
                return; // No rectangles in the pane

            // Build the edges
            java.util.List<Edge> edges =
                    new java.util.ArrayList<>();
            for (int i = 0; i < getChildren().size(); i++)
                for (int j = i + 1; j < getChildren().size(); j++)
                    if (overlaps((Rectangle)(getChildren().get(i)),
                            (Rectangle)(getChildren().get(j)))) {
                        edges.add(new Edge(i, j));
                        edges.add(new Edge(j, i));
                    }

            // Create a graph with rectangles as vertices
            Graph<Node> graph = new UnweightedGraph<>
                    (getChildren(), edges);
            UnweightedGraph<Node>.SearchTree tree = graph.dfs(0);
            boolean isAllRectanglesConnected = getChildren().size() == tree
                    .getNumberOfVerticesFound();

            for (Node rectangle: getChildren()) {
                if (isAllRectanglesConnected) { // All rectangles are connected
                    ((Rectangle)rectangle).setFill(Color.RED);
                    ((Rectangle)rectangle).setStroke(null);
                }
                else {
                    ((Rectangle)rectangle).setStroke(Color.BLACK);
                    ((Rectangle)rectangle).setFill(Color.WHITE);
                }
            }
        }
    }

    public static boolean overlaps(Rectangle r1, Rectangle r2) {
        // 4 conditions for an overlap to happen.
        return r1.getX() < (r2.getX() + r2.getWidth())
                && (r1.getX() + r1.getWidth()) > r2.getX()
                && r1.getY() < (r2.getY() + r2.getHeight())
                && (r1.getY() + r1.getHeight()) > r2.getY();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
