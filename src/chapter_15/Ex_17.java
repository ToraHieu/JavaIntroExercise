package chapter_15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Ex_17 extends Application {
    private ArrayList<Circle> points;
    private Rectangle rectangle;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();

        Text instruction = new Text(20, 30, "INSTRUCTION");
        Text add = new Text(20, 45, "Add: Left Click");
        Text remove = new Text(20, 60, "Remove: Right Click");
        Rectangle instructionRectangle = new Rectangle(10, 10, 140, 60);
        instructionRectangle.setStroke(Color.BLACK);
        instructionRectangle.setFill(Color.WHITE);

        pane.getChildren().addAll(instructionRectangle, instruction, add, remove);
        pane.setPadding(new Insets(15, 15, 15, 15));

        rectangle = new Rectangle(0, 0, 20, 20);
        rectangle.setFill(null);
        pane.getChildren().add(rectangle);

        points = new ArrayList<>();

        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Circle point = new Circle(event.getX(), event.getY(), 10);
                point.setFill(Color.WHITE);
                point.setStroke(Color.BLACK);
                points.add(point);
                pane.getChildren().add(point);
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                for (int i = 0; i < points.size(); i++) {
                    if (points.get(i).contains(event.getX(), event.getY())) {
                        pane.getChildren().remove(points.get(i));
                        points.remove(points.get(i));
                        i--;
                    }
                }
            }
            updateRectangle();
        });

        updateRectangle();
    }

    private void updateRectangle() {
        if (points.size() == 0) {
            rectangle.setStroke(null);
            return;
        } else {
            rectangle.setStroke(Color.BLACK);
        }

        double startX, startY, endX, endY;
        startX = points.get(0).getCenterX() - points.get(0).getRadius();
        startY = points.get(0).getCenterY() - points.get(0).getRadius();
        endX = points.get(0).getCenterX() + points.get(0).getRadius();
        endY = points.get(0).getCenterY() + points.get(0).getRadius();

        for (int i = 1; i < points.size(); i++) {
            Circle currentCircle = points.get(i);
            // Update X
            if (currentCircle.getCenterX() - currentCircle.getRadius() < startX)
                 startX = currentCircle.getCenterX() - currentCircle.getRadius();

            //Update Y
            if (currentCircle.getCenterY() - currentCircle.getRadius() < startY)
                startY = currentCircle.getCenterY() - currentCircle.getRadius();
            
            // Update endX
            if (currentCircle.getCenterX() + currentCircle.getRadius() > endX)
                endX = currentCircle.getCenterX() + currentCircle.getRadius();

            // Update endY
            if (currentCircle.getCenterY() + currentCircle.getRadius() > endY)
                endY = currentCircle.getCenterY() + currentCircle.getRadius();
        }

        rectangle.setX(startX);
        rectangle.setY(startY);
        rectangle.setWidth(endX - startX);
        rectangle.setHeight(endY - startY);
    }
}
