package chapter_30;

import java.util.Scanner;

public class Ex_10 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter a binary number: ");
            String binary = in.next();
            if (!binary.matches("[01]+")) {
                System.out.print("Not a binary number.");
                System.exit(1);
            }

            System.out.print("The decimal value is: " +
                    binary.chars().map(e -> e - '0').reduce(0, (e1, e2) -> (e1 << 1) + e2));
        }
    }
}
