package chapter_31;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_15 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(50);
        Pane pane = new Pane(circle);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_15");
        primaryStage.setScene(scene);
        primaryStage.show();

        double lineLength = 20;
        Point2D circleCenter = new Point2D(circle.getCenterX(), circle.getCenterY());
        for (double theta = 0; theta <= 360; theta += 10) {
            Point2D p1 = getPointOnCircle(circleCenter, circle.getRadius(), theta);
            Point2D p2 = getPointOnCircle(p1, lineLength, theta);
            Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            line.getStrokeDashArray().addAll(4d);
            line.setStrokeWidth(1);
            line.setStroke(Color.rgb(255,240,207));
            pane.getChildren().add(line);
            pane.setStyle("-fx-background-color: #abdbe3");
        }

        circle.setFill(null);
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.8, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.2, Color.rgb(238,74,47)),
                new Stop(0.6, Color.rgb(254,227,184)),
                new Stop(1.0, Color.rgb(255,240,207))
        );
        circle.setFill(gradient);
    }

    public static Point2D getPointOnCircle(Point2D center, double radius, double degree) {
        double x = center.getX() + radius * Math.cos(Math.toRadians(degree));
        double y = center.getY() + radius * Math.sin(Math.toRadians(degree));

        return new Point2D(x, y);
    }
}
