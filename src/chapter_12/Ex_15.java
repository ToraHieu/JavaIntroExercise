package chapter_12;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex_15 {
    public static void main(String[] args) throws Exception {
        File file = new File("Excercise12_15.txt");
        if (file.exists()) {
            System.out.println("File has already existed.");
            System.exit(1);
        }
        PrintWriter output = new PrintWriter(file);
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 101);     
            output.print(random + " ");
        }
        output.close();
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<>(100);
        while (scanner.hasNext()) { 
            list.add(scanner.nextInt());
        }
        scanner.close();
        java.util.Collections.sort(list);
        for(int i: list) {
            System.out.println(i);
        }
    }

}
