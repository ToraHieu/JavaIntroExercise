package chapter_13;

import java.util.Scanner;

public class Ex_17 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the first complex number: ");
            double a = input.nextDouble();
            double b = input.nextDouble();
            
            System.out.print("Enter the second complex number: ");
            double c = input.nextDouble();
            double d = input.nextDouble();
            
            Complex c1 = new Complex(a, b);
            Complex c2 = new Complex(c, d);
            
            System.out.println("(" + c1.toString() + ") + (" + c2.toString() + ") = " + c1.add(c2).toString());
            System.out.println("(" + c1.toString() + ") - (" + c2.toString() + ") = " + c1.subtract(c2).toString());
            System.out.println("(" + c1.toString() + ") * (" + c2.toString() + ") = " + c1.multiply(c2).toString());
            System.out.println("(" + c1.toString() + ") / (" + c2.toString() + ") = " + c1.divide(c2).toString());
            System.out.println("|(" + c1.toString() + ")| = " + c1.abs());
        }
    }
}
