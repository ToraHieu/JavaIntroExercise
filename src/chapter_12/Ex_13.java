package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_13 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: fileName");
            System.exit(1);
        }
        
        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
           System.out.println("Source file " + args[0] + " not exist");
           System.exit(2);
        }
        
        Scanner input = new Scanner(sourceFile);
        int lineCount = 0, wordCount = 0, charCount = 0;
        
        // This approach seems to take too many resources for a simple task.
        // Need to review other solutions for better optimization.
        while (input.hasNextLine()) {
            String s = input.nextLine();
            lineCount++;
            Scanner lineScan = new Scanner(s);
            while (lineScan.hasNext()) {
                String s2 = lineScan.next();
                wordCount++;
                charCount+= s2.length();
            }
            lineScan.close();
        }
        
        input.close();
        System.out.print("File " + sourceFile + " has\n" 
                + charCount + " characters\n" 
                + wordCount + " words\n"
                + lineCount + " lines");
    }
}
