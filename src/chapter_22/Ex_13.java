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

public class Ex_13 extends Application {
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
            if (points.size() > 2) {
                for (MyPoint myPoint : getConvexHull(points)) {
                    polygon.getPoints().add(myPoint.getX());
                    polygon.getPoints().add(myPoint.getY());
                }
            }
        });

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Ex_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Return the points that form a convex hull */
    public static ArrayList<MyPoint> getConvexHull(ArrayList<MyPoint> myPoints) {
        // Find and set rightMostLowestPoint
        MyPoint rightMostLowestPoint = myPoints.get(0); // Initialized rightMostLowestPoint
        for (int i = 1; i < myPoints.size(); i++) {
            if (rightMostLowestPoint.getY() < myPoints.get(i).getY()) { // Lowest
                rightMostLowestPoint = myPoints.get(i);
            } else if (rightMostLowestPoint.getY() == myPoints.get(i).getY()) {
                if (rightMostLowestPoint.getX() < myPoints.get(i).getX()) { // Rightmost
                    rightMostLowestPoint = myPoints.get(i);
                }
            }
        }
        MyPoint.setRightMostLowestPoint(rightMostLowestPoint);

        Collections.sort(myPoints);
        
        ArrayList<MyPoint> result = new ArrayList<>();
        result.add(myPoints.get(0));
        result.add(myPoints.get(1));
        result.add(myPoints.get(2));
        int i = 3;
        while (i < myPoints.size()) {
            MyPoint t1 = result.get(result.size() - 1), t2 = result.get(result.size() - 2);
            if (((t1.getX() - t2.getX()) * (myPoints.get(i).getY() - t2.getY()) - (myPoints.get(i).getX() - t2.getX()) * (t1.getY() - t2.getY())) > 0) {
                result.add(myPoints.get(i++));
            } else {
                result.remove(result.size() - 1);
            }
        }

        // Remove middle point that may have occurred in the first free points
        MyPoint p1 = result.get(0),
                p2 = result.get(1),
                p3 = result.get(2);
        if ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()) == (p3.getY() - p2.getY()) * (p2.getX() - p1.getX())) { // 3 points are aligned
            // Remove the middle point, i.e. the point closer to rightMostLowestPoint
            result.remove(MyPoint.middlePoint(p1, p2, p3));
        }

        return result;
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
