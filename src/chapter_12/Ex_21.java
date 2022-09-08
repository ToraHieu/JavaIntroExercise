package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_21 {
    public static void main(String[] args) throws Exception {
        File file = new File("SortedStrings.txt");
        if (!file.exists()) {
            System.out.println(file.getName() + " does not exist.");
            System.exit(1);
        }
        Scanner input = new Scanner(file);
        String previousString = input.nextLine();
        String currentString = input.nextLine();
        boolean sorted = true;
        while (input.hasNext()) {
            if (currentString.compareTo(previousString) < 0) {
                sorted = false;
                break;
            }
            previousString = currentString;
            currentString = input.nextLine();
        }
        input.close();
        if (sorted) 
            System.out.println("The strings in the file are sorted.");
        else {
            System.out.println("The strings in the file are not sorted.\n"
                    + "The first two lines are not sorted are:");
            System.out.println(previousString + "\n" + currentString);
        }
    }
}
