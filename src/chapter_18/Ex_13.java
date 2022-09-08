package chapter_18;

import java.util.Scanner;

public class Ex_13 {
    public static int max(int[] ints) {
        return max(ints, 1, ints[0]);
    }

    private static int max(int[] ints, int index, int max) {
        if (index == ints.length) {
            return max;
        }
        if (ints[index] > max)
            max = ints[index];
        return max(ints, index + 1, max);
    }

    public static void main(String[] args) {
        int[] list = new int[8];
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter 8 integers: ");
            for (int i = 0; i < 8; i++) {
                list[i] = input.nextInt();
            }
        }
        System.out.println("The largest number is: " + max(list));
    }
}
