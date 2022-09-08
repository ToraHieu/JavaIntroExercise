package chapter_05;

import java.util.Scanner;
/** Copied from the solution */
public class Ex_44 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int value = input.nextInt();

        System.out.print("The 8 bits are ");
        int mask = 1;
        for (int i = 7; i >= 0; i--) {
            int temp = value >> i;
            int bit = temp & mask;
            System.out.print(bit);
        }
    }
}
