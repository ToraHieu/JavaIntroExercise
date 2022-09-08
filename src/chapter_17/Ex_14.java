package chapter_17;

import java.io.*;
import java.util.Scanner;

public class Ex_14 {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter an input file: ");
            File inFile = new File(scanner.next());
            if (!inFile.exists()) {
                System.out.print("File not found. System is exiting.");
                System.exit(1);
            }

            System.out.print("Enter an output file: ");
            File outFile = new File(scanner.next());
            if (outFile.exists()) {
                System.out.print("File has already existed. System is exiting.");
                System.exit(2);
            }

            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inFile));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile))) {
                int b;
                while ((b = inputStream.read()) != -1) {
                    outputStream.write(b + 10);
                }
            }
        }
    }
}
