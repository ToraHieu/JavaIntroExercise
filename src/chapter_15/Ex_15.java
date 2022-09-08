package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Ex_15 extends Application {
    private ArrayList<Circle> points;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_15");
        primaryStage.setScene(scene);
        primaryStage.show();

        points = new ArrayList<>();

        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Circle point = new Circle(event.getX(), event.getY(), 10);
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
        });
    }
}
