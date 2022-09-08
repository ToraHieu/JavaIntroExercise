package chapter_22;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_09 {
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
//        for (double[] ss: s)
//            System.out.print(Arrays.toString(ss));
        System.out.println("The convex hull is");
        for (Point2D p: getConvexHull(s)) {
            System.out.print("(" + p.getX() + ", " + p.getY() + ") ");
        }
    }

    /** Return the points that form a convex hull */
    public static ArrayList<Point2D> getConvexHull(double[][] s) {
        ArrayList<Point2D> list = new ArrayList<>();

        /* Step 1:
        Given a list of points S, let the points in S be labeled s0, s1, . . . , sk.
        Select the rightmost lowest point S as h0. Add h0 to list H.
        (H is initially empty. H will hold all points in the convex hull after the algorithm is finished.)
        Let t0 be h0. */
        // Find h0
        double[] h0 = {s[0][X], s[0][Y]}; // Initialized h0
        for (int i = 1; i < s.length; i++) {
            if (h0[Y] < s[i][Y]) { // Lowest
                h0[X] = s[i][X];
                h0[Y] = s[i][Y];
            } else if (h0[Y] == s[i][Y]) {
                if (h0[X] < s[i][X]) { // Rightmost
                    h0[X] = s[i][X];
                    h0[Y] = s[i][Y];
                }
            }
        }
        list.add(new Point2D(h0[X], h0[Y]));
        double[] t0 = {h0[X], h0[Y]};

        /* Step 2:
        Let t1 be s0. For every point p in S,
        if p is on the right side of the direct line from t0 to t1, then let t1 be p.
         */
        /*
        Step 3:
        If t1 is h0, the points in H form a convex hull for S.
        Otherwise, add t1 to H, let t0 be t1, and go back to Step 2
         */
        for (int i = 1; i < s.length; i++) {
            double[] t1 = {s[0][X], s[0][Y]};
            // An edge case where t0 is the same as t1 (the first pair entered is in the convex hull)
            if (t0[X] == t1[X] && t0[Y] == t1[Y]) {
                t1[X] = s[1][X];
                t1[Y] = s[1][Y];
            }
            for (double[] p: s) {
                if ((t1[X] - t0[X])*(p[Y] - t0[Y]) - (p[X] - t0[X])*(t1[Y] - t0[Y]) > 0) {
                    t1[X] = p[X];
                    t1[Y] = p[Y];
                }
            }
            if (t1[X] == h0[X] && t1[Y] == h0[Y]) // t1 is h0
                break;

            list.add(new Point2D(t1[X], t1[Y]));
            t0[X] = t1[X];
            t0[Y] = t1[Y];
        }

        return list;
    }
}
