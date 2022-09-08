package chapter_12;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex_11 {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: replaceTex filename");
            System.exit(0);
        }
        File file = new File(args[1]);
        if (file.exists()) 
            System.out.println("The file exists, program's processing.");
        try (
            Scanner input = new Scanner(file); 
            PrintWriter output = new PrintWriter(new File("Ch12_Ex_11_output.txt"));
        ) {
            while (input.hasNext()) {
                String s = input.nextLine();
                String s2 = s.replaceAll(args[0], "");
                output.println(s2);
            }
        }
        System.out.println("Complete!");
    }

}
