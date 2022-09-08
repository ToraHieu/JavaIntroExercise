package chapter_23;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Ex_10 extends Application {
    private static final byte SPACE_PADDING = 5;
    private static final int FONT_SIZE = 14;

    private final TextField keyTextField = new TextField();
    private final Button insertKeyButton = new Button("Insert");
    private final Button removeRootButton = new Button("Remove the root");
    private final Insets padding = new Insets(SPACE_PADDING);

    private HeapPane heapPane = new HeapPane();

    @Override
    public void start(Stage primaryStage) {
        Text titleText = new Text("Heap Animation");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, FONT_SIZE));
        Text usageText = new Text("\nUsage: Enter an integer key and click the Insert button to insert the key into the heap. " +
                "Click the Remove the root button to remove the root from the heap.");
        usageText.setFont(Font.font("Arial", FONT_SIZE));
        Label topLabel = new Label(null, new TextFlow(titleText, usageText));

        Text instructionText = new Text("Enter a key:");
        HBox insertBox = new HBox(instructionText, keyTextField, insertKeyButton);
        insertBox.setAlignment(Pos.CENTER);
        insertBox.setPadding(padding);
        insertBox.setSpacing(SPACE_PADDING);
        insertBox.setStyle("-fx-border-color: green");

        HBox bottomBox = new HBox(insertBox, removeRootButton);
        bottomBox.setPadding(padding);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(SPACE_PADDING);

        BorderPane pane = new BorderPane();
        pane.setTop(topLabel);
        pane.setCenter(heapPane);
        pane.setBottom(bottomBox);
        pane.setPadding(padding);
        BorderPane.setMargin(heapPane, new Insets(10, 0, 10, 0));

        heapPane.heightProperty().addListener(ov -> heapPane.paint());
        heapPane.widthProperty().addListener(ov -> heapPane.paint());

        insertKeyButton.setOnMouseClicked(event -> heapPane.add(Integer.parseInt(keyTextField.getText())));
        removeRootButton.setOnMouseClicked(event -> heapPane.removeRoot());

        Scene scene = new Scene(pane, 1000, 600);
        primaryStage.setTitle("Ex_10");
        primaryStage.setScene(scene);
        primaryStage.show();

        heapPane.add(2);
        heapPane.add(3);
        heapPane.add(1);
        heapPane.add(5);
        heapPane.add(4);
        heapPane.add(5);
    }

    class HeapPane extends Pane {
        private static final int CIRCLE_RADIUS = 15;
        private Heap<Integer> heap;

        public HeapPane() {
            this.heap = new Heap<>();
            initialize();
        }

        public HeapPane(Integer[] list) {
            this.heap = new Heap<>(list);
            initialize();
        }

        private void initialize() {
            this.setStyle("-fx-border-color: red");
            this.setPadding(padding);
            this.paint();
        }

        private void paint() {
            this.getChildren().clear();
            if (heap.getSize() == 0) {
                Text emptyText = new Text("Heap is empty");
                emptyText.setX(this.getWidth() / 2 - 40); // Magic number: estimated half the length of the text.
                emptyText.setY(20); // Magic number: Goes down by 20 pixel

                this.getChildren().add(emptyText);
            } else {
                displayNode(0, new Point2D(this.getWidth() / 2, 25), this.getWidth() / 4);
            }
        }

        private void displayNode(int index, Point2D centerPoint, double xLength) {
            // Check if this node has children
            // Left child
            if (2 * index + 1 < this.heap.list.size()) {
                Point2D leftCenterPoint = new Point2D(
                        centerPoint.getX() - xLength, centerPoint.getY() + CIRCLE_RADIUS * 5);
                Line line = new Line(centerPoint.getX(), centerPoint.getY(),
                        leftCenterPoint.getX(), leftCenterPoint.getY());

                this.getChildren().add(line);
                displayNode(2 * index + 1, leftCenterPoint, xLength / 2);
            }

            // Right child
            if (2 * index + 2 < this.heap.list.size()) {
                Point2D rightCenterPoint = new Point2D(
                        centerPoint.getX() + xLength, centerPoint.getY() + CIRCLE_RADIUS * 5);
                Line line = new Line(centerPoint.getX(), centerPoint.getY(),
                        rightCenterPoint.getX(), rightCenterPoint.getY());

                this.getChildren().add(line);
                displayNode(2 * index + 2, rightCenterPoint, xLength / 2);
            }

            // Draw the node after the line(s) so it will hide the interacted part.
            CircleNode circleNode = new CircleNode(this.heap.list.get(index));
            this.getChildren().add(circleNode);
            circleNode.setTranslateX(centerPoint.getX() - CIRCLE_RADIUS);
            circleNode.setTranslateY(centerPoint.getY() - CIRCLE_RADIUS);
        }

        private void add(Integer integer) {
            this.heap.add(integer);
            this.paint();
        }

        private void removeRoot() {
            this.heap.remove();
            this.paint();
        }

        class CircleNode extends StackPane {
            CircleNode(int i) {
                Circle circle = new Circle(CIRCLE_RADIUS);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                Text text = new Text(i + "");
                text.setTextAlignment(TextAlignment.CENTER);
                this.getChildren().addAll(circle, text);
            }
        }
    }
}
