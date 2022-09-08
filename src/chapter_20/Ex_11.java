package chapter_20;

import java.util.Scanner;
import java.util.Stack;

public class Ex_11 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        try (Scanner input = new Scanner(System.in)) {
            for (int i = 0; i < 10; i++) {
                stack.push(input.nextInt());
            }
        }

        int currentInt = stack.pop();
        System.out.print(currentInt);
        do {
            if (stack.peek() != currentInt) {
                currentInt = stack.pop();
                System.out.print(" " + currentInt);
            }
            else
                stack.pop();
        }
        while (!stack.empty());
    }
}
