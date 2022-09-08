package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_14 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file: ");
        String s = input.next();
        input.close();
        File file = new File(s);
        if (!file.exists()) {
            System.out.println("File doesn't exist.");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(file);
        double total = 0;
        int count = 0;
        while (fileReader.hasNext()) {
            total+= fileReader.nextDouble();
            count++;
        }
        double average = total / count;
        fileReader.close();
        System.out.println("The total is " +  total
                + " and average is " + average);
    }

}
