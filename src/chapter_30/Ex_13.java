package chapter_30;

public class Ex_13 {
    public static void main(String[] args) {
        // Prompt the user to enter a string
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = input.nextLine();

        System.out.println("The number of letters is " + countLetters(s));
    }

    public static int countLetters(String s) {
        return s.chars().reduce(0, (e1, e2) -> (Character.isLetter(e2) ? e1+1 : e1));
    }
}
