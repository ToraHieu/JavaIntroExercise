package chapter_18;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/** Undone due to lack of knowledge in geometry (and the will to research it properly).
 * There are techniques out there that doesn't require manual calculation. Check on them if you want to.*/
public class Ex_27 extends Application {
    @Override
    public void start(Stage primaryStage) {
        KochSnowflakePane pane = new KochSnowflakePane();
        TextField tfOrder = new TextField("0");
        tfOrder.setOnAction(e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

        // Pane to hold label, text field, and a button
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        pane.widthProperty().addListener(ov -> pane.paint());
        pane.heightProperty().addListener(ov -> pane.paint());

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 210);
        primaryStage.setTitle("Ex_19"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    class KochSnowflakePane extends Pane {
        private int order = 0;
        private Polygon snowflake = new Polygon();

        public KochSnowflakePane() {
            this.getChildren().add(snowflake);
            snowflake.setStroke(Color.BLACK);
            snowflake.setFill(Color.WHITE);
        }

        public void setOrder(int order) {
            this.order = order;
        }

        protected void paint() {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2 , 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            snowflake.getPoints().clear(); // Clear the pane before redisplay

            setupSnowflake(order, p1, p2, p3);
        }

        private void setupSnowflake(int order, Point2D p1, Point2D p2, Point2D p3) {
            if (order == 0) {
                // Add three points into the snowflake
                snowflake.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
            } else {

                // Getting points for outward triangle of side p1-p2
                // Notation for ratio m:n : p112(1:2); p121(2:1); p102: Outward point
                // This is the Section formula (Point that divides a line in given ratio)
                Point2D p112 = new Point2D((1 * p2.getX() + 2 * p1.getX()) / (1 + 2), (1 * p2.getY() + 2 * p1.getY()) / (1 + 2));
                Point2D p121 = new Point2D((2 * p2.getX() + 1 * p1.getX()) / (2 + 1), (2 * p2.getY() + 1 * p1.getY()) / (2 + 1));

                // Ok, so now we just need to calculate the last point and it's done.
                // Where do I get the formula for it though?
                // Ah shiet, I can't find it. Calling it a quit and moving on to the next. Friggin geometry.
                Point2D p102 = new Point2D((p121.getX() - p112.getX())/2, 0);

            }
        }
    }
}
