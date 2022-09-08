package chapter_18;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_35 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HTreePane pane = new HTreePane();
        TextField tfOrder = new TextField("0");
        tfOrder.setOnAction(e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

        // Pane to hold label, and a text field
        HBox hBox = new HBox(10);

        Label label = new Label("Enter an order: ", tfOrder);
        label.setContentDisplay(ContentDisplay.RIGHT);
        hBox.getChildren().addAll(label);
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

    class HTreePane extends Pane {
        int order;

        public HTreePane() {
        }

        void paint() {
            this.getChildren().clear();
            displayH(order, new Point2D(getWidth() / 2, getHeight() / 2), Math.min(getWidth() / 2 - 10, getHeight() / 2 - 10));
        }

        void setOrder(int order) {
            this.order = order;
            paint();
        }

        void displayH(int order, Point2D centerPoint, double length) {
            double radius = length / 2;
            // Get all the points needed.
            Point2D centerLeft, centerRight, upperLeft, lowerLeft, upperRight, lowerRight;
            centerLeft = centerPoint.subtract(radius, 0);
            centerRight = centerPoint.add(radius, 0);
            upperLeft = centerLeft.subtract(0, radius);
            lowerLeft = centerLeft.add(0, radius);
            upperRight = centerRight.subtract(0, radius);
            lowerRight = centerRight.add(0, radius);

            this.getChildren().addAll(
                    new Line(centerLeft.getX(), centerLeft.getY(), centerRight.getX(), centerRight.getY()),
                    new Line(upperLeft.getX(), upperLeft.getY(), lowerLeft.getX(), lowerLeft.getY()),
                    new Line(upperRight.getX(), upperRight.getY(), lowerRight.getX(), lowerRight.getY())
            );

            // Base case
            if (order == 0)
                return;
            order--;
            // Radius of this order is the length of the next order
            displayH(order, upperLeft, radius);
            displayH(order, lowerLeft, radius);
            displayH(order, upperRight, radius);
            displayH(order, lowerRight, radius);
        }
    }
}
