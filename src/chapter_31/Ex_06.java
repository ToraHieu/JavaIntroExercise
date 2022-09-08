package chapter_31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Ex_06 extends Application {
    private final Circle eye1 = new Circle();
    private final Circle eye2 = new Circle();
    private final Ellipse face = new Ellipse();
    private final Pane pane = new Pane();

    @Override
    public void start(Stage primaryStage) {
        eye1.setFill(Color.WHITE);
        eye2.setFill(Color.WHITE);

        face.centerXProperty().bind(pane.widthProperty().divide(2));
        face.centerYProperty().bind(pane.heightProperty().divide(2));
        face.radiusXProperty().bind(pane.widthProperty().multiply(7).divide(16));
        face.radiusYProperty().bind(pane.heightProperty().multiply(7).divide(16));

        pane.getChildren().addAll(face, eye1, eye2);
        pane.widthProperty().addListener(ov -> resize());
        pane.heightProperty().addListener(ov -> resize());

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resize() {
        double eyesRadius = Math.min(pane.getWidth(), pane.getHeight()) / 8;
        eye1.setCenterX(pane.getWidth()/2 - eyesRadius - pane.getWidth()/16);
        eye2.setCenterX(pane.getWidth()/2 + eyesRadius + pane.getWidth()/16);
        double eyesCenterY = pane.getHeight() / 2 - eyesRadius;
        eye1.setCenterY(eyesCenterY);
        eye2.setCenterY(eyesCenterY);
        eye1.setRadius(eyesRadius);
        eye2.setRadius(eyesRadius);
    }
}
