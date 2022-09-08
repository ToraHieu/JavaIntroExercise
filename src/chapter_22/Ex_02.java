package chapter_22;

import java.util.Scanner;

/** (Efficient polynomial calculation)
 * For a polynomial f(x) = a_n x^n + a_n-1 x^n-1 + a_n-2 x^n-2 + ... + a_1 x + a_0,
 * write an efficient program that prompts the user to enter the degree n,
 * the coefficients a0 to an, and the value for x, and computes and displays ƒ(x).*/
public class Ex_02 {
    public static void main(String[] args) {
        int n;
        double x, result;
        double[] coefficients;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter n: ");
            n = input.nextInt();
            coefficients = new double[n + 1];

            System.out.print("Enter x: ");
            x = input.nextInt();

            for (int i = 0; i <= n; i++) {
                System.out.print("Enter a" + i + ": ");
                coefficients[i] = input.nextDouble();
            }
        }
        /* This is not the most efficient because the Math.pow method has a complexity of O(nlogn)
        // Calculate the polynomial using Horner's method
        for (int order = n; order >= 0; order--) {
            result += Math.pow(x, order) * coefficients[order];
        }
        */

        // This is a better implementation with complexity of O(n)
        double valueOfXPowerN = 1; // n = 0
        result = coefficients[0];
        for (int order = 1; order <= n; order++) {
            result += (valueOfXPowerN *= x) * coefficients[order];
        }

        System.out.printf("f(%f) = %f", x, result);
    }
}
