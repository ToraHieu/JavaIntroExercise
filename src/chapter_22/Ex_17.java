package chapter_22;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.*;

public class Ex_17 extends Application {
    // Should implement binding for this ArrayList with the children of the pane
    private ArrayList<Point2D> points = new ArrayList<>();
    private static final int DOT_RADIUS = 5;
    private Line line = new Line();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.getChildren().add(line);
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                points.add(new Point2D(e.getX(), e.getY()));
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
            if (points.size() > 1) {
                Point2D[] point2DS = new Point2D[points.size()];
                point2DS = points.toArray(point2DS);
                Pair closestPair = getClosestPair(point2DS);
                line.setVisible(true);
                line.setStartX(closestPair.getP1().getX());
                line.setEndX(closestPair.getP2().getX());
                line.setStartY(closestPair.getP1().getY());
                line.setEndY(closestPair.getP2().getY());
            } else {
                line.setVisible(false);
            }
        });

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Ex_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Return the distance of the closest pair of points */
    public static Pair getClosestPair(double[][] points) {
        return null;
    }

    /** Return the pair with the closest distance of points */
    public static Pair getClosestPair(Point2D[] points) {
        // Convert to an ArrayList for optimized sorting
        ArrayList<Point2D> pointsOrderedOnX = new ArrayList<>(Arrays.asList(points));
        pointsOrderedOnX.sort(((Comparator<Point2D>) (p1, p2) -> (int) (p1.getX() - p2.getX()))
                .thenComparing((p1, p2) -> (int) (p1.getY() - p2.getY())));

        //noinspection unchecked
        ArrayList<Point2D> pointsOrderedOnY = (ArrayList<Point2D>)pointsOrderedOnX.clone();
        pointsOrderedOnY.sort(((Comparator<Point2D>) (p1, p2) -> (int) (p1.getY() - p2.getY()))
                .thenComparing((p1, p2) -> (int) (p1.getX() - p2.getX())));

        Point2D[] arrayPointsOrderedOnX = new Point2D[pointsOrderedOnX.size()];
        arrayPointsOrderedOnX = pointsOrderedOnX.toArray(arrayPointsOrderedOnX);
        Point2D[] arrayPointsOrderedOnY = new Point2D[pointsOrderedOnY.size()];
        arrayPointsOrderedOnY = pointsOrderedOnY.toArray(arrayPointsOrderedOnY);

        return distance(arrayPointsOrderedOnX, 0, points.length - 1, arrayPointsOrderedOnY);
    }

    /** Return the distance of the closest pair of points
     * in pointsOrderedOnX[low..high]. This is a recursive
     * method. pointsOrderedOnX and pointsOrderedOnY are
     * not changed in the subsequent recursive calls.
     */
    public static Pair distance(Point2D[] pointsOrderedOnX, int low, int high, Point2D[] pointsOrderedOnY) {
        if (high - low == 2) {
            Pair closest = new Pair(pointsOrderedOnX[low], pointsOrderedOnX[low + 1]);
            if (closest.getDistance() > pointsOrderedOnX[low + 1].distance(pointsOrderedOnX[high]))
                closest = new Pair(pointsOrderedOnX[low + 1], (pointsOrderedOnX[high]));
            if (closest.getDistance() > pointsOrderedOnX[low].distance(pointsOrderedOnX[high]))
                closest = new Pair(pointsOrderedOnX[low], (pointsOrderedOnX[high]));
            return closest;
        } else if (high - low == 1) {
            return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

        // Step 2
        /* Divide S into two subsets, S1 and S2, of equal size using the midpoint in the sorted list.
        Let the midpoint be in S1. Recursively find the closest pair in S1 and S2.*/
        int midIndex = low + (high - low) / 2;
        Pair d1 = distance(pointsOrderedOnX, low, midIndex, pointsOrderedOnY);
        Pair d2 = distance(pointsOrderedOnX, midIndex + 1, high, pointsOrderedOnY);

        // Step 3
        /* Find the closest pair between a point in S1 and a point in S2
        and denote their distance as d3. The closest pair is the one
        with the distance min(d1, d2, d3).*/

        // d = min(d1, d2)
        Pair d = d1.getDistance() < d2.getDistance() ? d1 : d2;
        Point2D midPoint = pointsOrderedOnX[midIndex];
        ArrayList<Point2D> stripL = new ArrayList<>(), stripR = new ArrayList<>();

        List<Point2D> alPointsX = Arrays.asList(pointsOrderedOnX);
        for (Point2D p: pointsOrderedOnY) {
            if (alPointsX.indexOf(p) <= midIndex && midPoint.getX() - p.getX() <= d.getDistance()) {
                stripL.add(p);
            } else if (alPointsX.indexOf(p) > midIndex && p.getX() - midPoint.getX() <= d.getDistance()) {
                stripR.add(p);
            }
        }

        int r = 0; // r is the index of a point in stripR
        for (Point2D p: stripL) {
            // Skip the points in stripR below p.y - d
            while (r < stripR.size() && stripR.get(r).getY() <= p.getY() - d.getDistance()) {
                r++;
            }

            int r1 = r;
            while (r1 < stripR.size() && Math.abs(stripR.get(r1).getY() - p.getY()) <= d.getDistance()) {
                // Check if (p, stripR.get(r1) is a possible closest pair
                if (distance(p, stripR.get(r1)) < d.getDistance()) {
                    d = new Pair(p, stripR.get(r1));
                }

                r1++;
            }
        }

        return d;
    }

    /** Compute the distance between two points p1 and p2 */
    public static double distance(Point2D p1, Point2D p2) {
        return p1.distance(p2);
    }

    /** Compute the distance between points (x1, y1) and (x2, y2) */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}

class Pair {
    private Point2D p1, p2;

    Pair(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public double getDistance() {
        return p1.distance(p2);
    }
}
