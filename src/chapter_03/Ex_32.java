package chapter_03;

import java.util.Scanner;

/** Global edition
 * (Geometry: point position)
 * Given a directed line from point p0(x0, y0) to p1(x1, y1),
 * you can use the following condition to decide whether a point p2(x2, y2)
 * is on the left of the line, on the right, or on the same line.*/
public class Ex_32 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter three points for p0, p1 and p2: ");
        double x0, y0, x1, y1, x2, y2, result;
        x0 = input.nextDouble();
        y0 = input.nextDouble();
        x1 = input.nextDouble();
        y1 = input.nextDouble();
        x2 = input.nextDouble();
        y2 = input.nextDouble();
        result = (x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0);

        if (result > 0)
            System.out.print("p2 is on the left side of the line");
        else if (result == 0)
            System.out.print("p2 is on the same line");
        else
            System.out.print("p2 is on the right side of the line");
    }
}
