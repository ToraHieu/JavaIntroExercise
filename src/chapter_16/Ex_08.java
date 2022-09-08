package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_08 extends Application {
    private Circle circle1 = new Circle();
    private Circle circle2 = new Circle();
    private Text status = new Text("Two circles intersect? No");

    @Override
    public void start(Stage primaryStage) {
        Button btnRedraw = new Button("Redraw Circles");

        // Pane to draw the circles
        Pane paneForCircles = new Pane();
        paneForCircles.setPrefWidth(300);
        paneForCircles.setPrefHeight(200);
        paneForCircles.getChildren().addAll(circle1, circle2);

        // Each pane for each circle's properties
        PaneForCircleProperties paneForCircle1 = new PaneForCircleProperties(1);
        PaneForCircleProperties paneForCircle2 = new PaneForCircleProperties(2);

        // HBox for 2 circles' properties pane
        HBox paneForProperties = new HBox();
        paneForProperties.getChildren().addAll(paneForCircle1,paneForCircle2);
        paneForProperties.setSpacing(5);
        paneForProperties.setPadding(new Insets(5));
        paneForProperties.setAlignment(Pos.CENTER);

        // BorderPane HBox above and the Redraw button
        BorderPane paneForControls = new BorderPane();
        paneForControls.setCenter(paneForProperties);
        paneForControls.setBottom(btnRedraw);

        // The main layout of the program
        BorderPane pane = new BorderPane();
        pane.setTop(status);
        pane.setCenter(paneForCircles);
        pane.setBottom(paneForControls);

        BorderPane.setAlignment(status, Pos.CENTER);
        BorderPane.setAlignment(btnRedraw, Pos.CENTER);
        
        // Initialize the properties of the circles and their pane
        circle1.setCenterX(50);
        circle1.setCenterY(50);
        circle1.setRadius(30);
        circle1.setStroke(Color.BLACK);
        circle1.setFill(new Color(0,0, 0,0));
        paneForCircle1.updateProperties(circle1);
        circle2.setCenterX(180);
        circle2.setCenterY(60);
        circle2.setRadius(50);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(new Color(0,0, 0,0));
        paneForCircle2.updateProperties(circle2);

        // Set event handlers
        circle1.setOnMouseDragged(event -> {
            circle1.toFront();
            circle1.setCenterX(event.getX());
            circle1.setCenterY(event.getY());
            paneForCircle1.updateProperties(circle1);
            updateStatus();
        });

        circle2.setOnMouseDragged(event -> {
            circle2.toFront();
            circle2.setCenterX(event.getX());
            circle2.setCenterY(event.getY());
            paneForCircle2.updateProperties(circle2);
            updateStatus();
        });

        btnRedraw.setOnAction(event -> {
            circle1.setCenterX(paneForCircle1.getCenterX());
            circle1.setCenterY(paneForCircle1.getCenterY());
            circle1.setRadius(paneForCircle1.getRadius());

            circle2.setCenterX(paneForCircle2.getCenterX());
            circle2.setCenterY(paneForCircle2.getCenterY());
            circle2.setRadius(paneForCircle2.getRadius());

            updateStatus();
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_08");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateStatus() {
        double distance = Math.sqrt((circle1.getCenterX() - circle2.getCenterX()) * (circle1.getCenterX() - circle2.getCenterX())
        + (circle1.getCenterY() - circle2.getCenterY()) * (circle1.getCenterY() - circle2.getCenterY()));
        if (distance < circle1.getRadius() + circle2.getRadius())
            status.setText("Two circles intersect? Yes");
        else
            status.setText("Two circles intersect? No");
    }
    
    class PaneForCircleProperties extends BorderPane {
        private TextField tfCenterX = new TextField();
        private TextField tfCenterY = new TextField();
        private TextField tfRadius = new TextField();
        
        PaneForCircleProperties(int circleIndex) {
            GridPane properties = new GridPane();
            properties.addRow(0, new Text("Center x:"), tfCenterX);
            properties.addRow(1, new Text("Center y:"), tfCenterY);
            properties.addRow(2, new Text("Radius:"), tfRadius);
            properties.setHgap(5);

            tfCenterX.setAlignment(Pos.CENTER_RIGHT);
            tfCenterX.setPrefColumnCount(3);
            tfCenterY.setAlignment(Pos.CENTER_RIGHT);
            tfCenterY.setPrefColumnCount(3);
            tfRadius.setAlignment(Pos.CENTER_RIGHT);
            tfRadius.setPrefColumnCount(3);

            setTop(new Text("Enter circle " + circleIndex + " info:"));
            setCenter(properties);

            setStyle("-fx-border-color: black");
            setPadding(new Insets(5));
        }

        public void updateProperties(Circle circle) {
            tfCenterX.setText("" + circle.getCenterX());
            tfCenterY.setText("" + circle.getCenterY());
            tfRadius.setText("" + circle.getRadius());
        }

        public double getCenterX() {
            return Double.parseDouble(tfCenterX.getText());
        }

        public double getCenterY() {
            return Double.parseDouble(tfCenterY.getText());
        }

        public double getRadius() {
            return Double.parseDouble(tfRadius.getText());
        }
    }
}
