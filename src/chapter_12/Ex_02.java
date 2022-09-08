package chapter_12;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex_02 {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int i1, i2, sum;
        while (true) {
            try {
                System.out.print("Enter integer 1: ");
                i1 = input.nextInt();
                break;
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                input.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter integer 2: ");
                i2 = input.nextInt();
                break;
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                input.nextLine();
            }
        }
        sum = i1 + i2;
        System.out.println("The sum of two integers is: " + sum);
    }

}
