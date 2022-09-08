package chapter_22;

import java.util.Scanner;

public class GCDEuclid {
    /** Find GCD for integers m and n */
    public static long gcd(long m, long n) {
        if (m % n == 0)
            return n;
        else
            return gcd(n, m % n);
    }

    /** Main method */
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter two integers
        System.out.print("Enter first integer: ");
        long m = input.nextLong();
        System.out.print("Enter second integer: ");
        long n = input.nextLong();

        System.out.println("The greatest common divisor for " + m + " and " + n + " is " + gcd(m, n));
    }
}
