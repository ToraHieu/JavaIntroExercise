package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_16 {
    private static Scanner input;

    public static void main(String[] args) {
        int number1 = (int) (Math.random() * 10);
        int number2 = (int) (Math.random() * 10);

        // Create a Scanner
        input = new Scanner(System.in);

        System.out.print("What is " + number1 + " + " + number2 + "? ");
        int answer = input.nextInt();
        ArrayList<Integer> answers = new ArrayList<>();
        while (number1 + number2 != answer) {
            boolean isAnswered = false;
            for (int i = 0; i < answers.size(); i++) {
                if (answer == answers.get(i)) {
                    isAnswered = true;
                    break;
                }
            }
            if (isAnswered) {
                System.out.println("You already entered " + answer);
            } else {
                answers.add(answer);
            }
            System.out.print("Wrong answer. Try again. What is " + number1 + " + " + number2 + "? ");
            answer = input.nextInt();
        }

        System.out.println("You got it!");
    }

}
