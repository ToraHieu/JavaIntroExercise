package chapter_22;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Ex_15 extends Application {
    private static final int DOT_RADIUS = 5;

    // Should implement binding for this ArrayList with the children of the pane
    private ArrayList<MyPoint> points = new ArrayList<>();
    private Polygon polygon = new Polygon();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.getChildren().add(polygon);
        polygon.setFill(null);
        polygon.setStroke(Color.BLACK);
        
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                points.add(new MyPoint(e.getX(), e.getY()));
                pane.getChildren().add(new Circle(e.getX(), e.getY(), DOT_RADIUS));
            }
            if (e.getButton() == MouseButton.SECONDARY) {
                for (int i = 1; i < pane.getChildren().size(); i++) { // pane.getChildren().get(0) is the Line
                    if (pane.getChildren().get(i).contains(e.getX(), e.getY())) {
                        for (int j = 0; j < points.size(); j++) {
                            if (points.get(j).getX() == ((Circle)pane.getChildren().get(i)).getCenterX() &&
                                    points.get(j).getY() == ((Circle)pane.getChildren().get(i)).getCenterY()) {
                                points.remove(j);
                                j--;
                            }
                        }
                        pane.getChildren().remove(i);
                        i--;
                    }
                }
            }
            polygon.getPoints().clear();
            setConvexHull();
            if (points.size() > 2) {
                for (MyPoint myPoint : points) {
                    polygon.getPoints().add(myPoint.getX());
                    polygon.getPoints().add(myPoint.getY());
                }
            }
        });

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Ex_15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Return the points that form a convex hull */
    private void setConvexHull() {
        // Find and set rightMostLowestPoint
        MyPoint rightMostLowestPoint = points.get(0); // Initialized rightMostLowestPoint
        for (int i = 1; i < points.size(); i++) {
            if (rightMostLowestPoint.getY() < points.get(i).getY()) { // Lowest
                rightMostLowestPoint = points.get(i);
            } else if (rightMostLowestPoint.getY() == points.get(i).getY()) {
                if (rightMostLowestPoint.getX() < points.get(i).getX()) { // Rightmost
                    rightMostLowestPoint = points.get(i);
                }
            }
        }
        MyPoint.setRightMostLowestPoint(rightMostLowestPoint);

        Collections.sort(points);
    }


    private static class MyPoint extends Point2D implements Comparable<MyPoint> {
        static MyPoint rightMostLowestPoint;

        public MyPoint(double x, double y) {
            super(x, y);
        }

        public static void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }

        /** Return the point that is between other points */
        public static MyPoint middlePoint(MyPoint a, MyPoint b, MyPoint c) {
            double d_ab, d_ac, d_bc;
            d_ab = (a.getX() - b.getX())*(a.getX() - b.getX()) - (a.getY() - b.getY())*(a.getY() - b.getY());
            d_ac = (a.getX() - c.getX())*(a.getX() - c.getX()) - (a.getY() - c.getY())*(a.getY() - c.getY());
            d_bc = (b.getX() - c.getX())*(b.getX() - c.getX()) - (b.getY() - c.getY())*(b.getY() - c.getY());
            if (d_ab > d_ac && d_ab > d_bc)
                return c;
            else if (d_ac > d_ab && d_ac > d_bc)
                return b;
            else
                return a;
        }

        @Override
        public int compareTo(MyPoint o) {
            // rightMostLowestPoint is the smallest (first) element
            if (this == rightMostLowestPoint)
                return -1;

            return -(int)((getX() - rightMostLowestPoint.getX())*(o.getY() - rightMostLowestPoint.getY()) -
                    (o.getX() - rightMostLowestPoint.getX())*(getY() - rightMostLowestPoint.getY()));
        }
    }
}
