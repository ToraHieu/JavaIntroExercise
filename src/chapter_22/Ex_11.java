package chapter_22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ex_11 {
    private static final int X = 0, Y = 1;
    public static void main(String[] args) {
        double[][] s;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("How many points are in the set? ");
            s = new double[input.nextInt()][2];
            System.out.print("Enter " + s.length + " points: ");
            for (int i = 0; i < s.length; i++) {
                s[i][X] = input.nextDouble();
                s[i][Y] = input.nextDouble();
            }
        }

        System.out.print("The convex hull is");
        for (MyPoint p: getConvexHull(s)) {
            System.out.print(" (" + p.x + ", " + p.y + ")");
        }
    }

    /** Return the points that form a convex hull */
    public static ArrayList<MyPoint> getConvexHull(double[][] s) {
        // Convert double[][] to MyPoint[]
        MyPoint[] myPoints = new MyPoint[s.length];
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i] = new MyPoint(s[i][X], s[i][Y]);
        }

        // Find and set rightMostLowestPoint
        MyPoint rightMostLowestPoint = myPoints[0]; // Initialized rightMostLowestPoint
        for (int i = 1; i < myPoints.length; i++) {
            if (rightMostLowestPoint.y > myPoints[i].y) { // Lowest
                rightMostLowestPoint = myPoints[i];
            } else if (rightMostLowestPoint.y == myPoints[i].y) {
                if (rightMostLowestPoint.x < myPoints[i].x) { // Rightmost
                    rightMostLowestPoint = myPoints[i];
                }
            }
        }
        MyPoint.setRightMostLowestPoint(rightMostLowestPoint);

        Arrays.sort(myPoints);
        
        ArrayList<MyPoint> list = new ArrayList<>();
        list.add(myPoints[0]);
        list.add(myPoints[1]);
        list.add(myPoints[2]);
        int i = 3;
        while (i < myPoints.length) {
            MyPoint t1 = list.get(list.size() - 1), t2 = list.get(list.size() - 2);
            if (((t1.x - t2.x) * (myPoints[i].y - t2.y) - (myPoints[i].x - t2.x) * (t1.y - t2.y)) > 0) {
                list.add(myPoints[i++]);
            } else {
                list.remove(list.size() - 1);
            }
        }

        // Remove middle point that may have occurred in the first free points
        MyPoint p1 = list.get(0),
                p2 = list.get(1),
                p3 = list.get(2);
        if ((p2.y - p1.y) * (p3.x - p2.x) == (p3.y - p2.y) * (p2.x - p1.x)) { // 3 points are aligned
            // Remove the middle point, i.e. the point closer to rightMostLowestPoint
            list.remove(MyPoint.middlePoint(p1, p2, p3));
        }


        return list;
    }

    private static class MyPoint implements Comparable<MyPoint> {
        double x, y;

        static MyPoint rightMostLowestPoint;

        public MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }

        /** Return the point that is between other points */
        public static MyPoint middlePoint(MyPoint a, MyPoint b, MyPoint c) {
            double d_ab, d_ac, d_bc;
            d_ab = (a.x - b.x)*(a.x - b.x) - (a.y - b.y)*(a.y - b.y);
            d_ac = (a.x - c.x)*(a.x - c.x) - (a.y - c.y)*(a.y - c.y);
            d_bc = (b.x - c.x)*(b.x - c.x) - (b.y - c.y)*(b.y - c.y);
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

            return -(int)((x - rightMostLowestPoint.x)*(o.y - rightMostLowestPoint.y) -
                    (o.x - rightMostLowestPoint.x)*(y - rightMostLowestPoint.y));
        }
    }
}
