package chapter_12;

import java.util.Scanner;

public class Ex_03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*100);
        }
        boolean done = false;
        while (!done) {
            System.out.print("Enter an index: ");
            int i = input.nextInt();
            try {
                System.out.println("The corresponding element in array is: " + array[i]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of Bounds");
            }
        }
        input.close();
    }
}
