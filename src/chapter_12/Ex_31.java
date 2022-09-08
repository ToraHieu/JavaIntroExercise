package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_31 {
    public static void main(String[] args) throws Exception {
        Scanner consoleInput = new Scanner(System.in);
        System.out.print("Enter the year: ");
        int year = consoleInput.nextInt();
        System.out.print("Enter the gender: ");
        String s = consoleInput.next();
        char gender = s.charAt(0);
        System.out.print("Enter the name: ");
        String name = consoleInput.next();
        consoleInput.close();
        
        String fileName = "D:/DH16LT/Java/Excercise12/babynamesranking" + year + ".txt";
        Scanner input = new Scanner(new File(fileName));
        
        for (int i = 1; i <= 1000; i++) {
            // Jumping to the desired name location.
            if (gender == 'M' || gender == 'm') {
                input.next();
            } else {
                input.next();
                input.next();
                input.next();
            }
            String currentName = input.next();
            if (currentName.equals(name)) {
                System.out.println(name + " is ranked #" + i + " in year " + year);
                System.exit(0);
            }
            input.nextLine();
        }
        System.out.println("The name " + name + " is not ranked in year " + year);
        input.close();
    }
}
