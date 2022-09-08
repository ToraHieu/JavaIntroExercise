package chapter_03;

import java.util.Scanner;

public class Ex_01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, b, c;
        System.out.print("Enter a, b, c: ");
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double r1 = (-b + Math.pow(d, 0.5)) / 2 * a;
            double r2 = (-b - Math.pow(d, 0.5)) / 2 * a;
            System.out.println("The equation has two roots " + r1 + " and " + r2);
        } else if (d == 0) {
            double r = (-b + Math.pow(d, 0.5)) / 2 * a;
            System.out.println("The equation has one root " + r);
        } else {
            System.out.println("The equation has no real roots");
        }
        input.close();
    }
}
