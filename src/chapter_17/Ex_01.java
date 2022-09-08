package chapter_17;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Ex_01 {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter output = new PrintWriter((new FileOutputStream("Exercise17_01.txt", true)));
        for (int i = 0; i < 150; i++) {
            output.print((int)(Math.random() * 100) + " ");
        }
        output.close();
    }
}
