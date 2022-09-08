package chapter_13;

import java.util.Scanner;

public class Ex_19 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a decimal number: ");
            String s = input.next();
            String[] arr = s.split("[.]");

            // Getting the integer part
            Rational_15 r1 = new Rational_15(Long.parseLong(arr[0]), 1);

            // Getting the fractional part
            long numerator = Long.parseLong(arr[1]);
            long denominator = 1;
            while (denominator - numerator < 0)
                denominator *= 10;
            Rational_15 r2 = new Rational_15(numerator, denominator);

            // Combining to become a complete rational number.
            Rational_15 r;
            if (s.charAt(0) == '-')
                r = r1.subtract(r2);
            else
                r = r1.add(r2);
            System.out.println("The fraction number is " + r.toString());
        }
    }
}
