package chapter_12;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/** Made based on the idea from the solution of Excercise12_28.
 *  Need more optimization. This approach seems to take too many resources.*/
public class Ex_27 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            if (file.isFile()) {
                replaceWords(file);
            }
        }
        System.out.println("Complete");
    }

    private static void replaceWords(File file) throws Exception {
        Scanner input = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] words = line.split(" ");
            for (String s : words) {
                String s2 = s;
                if (s.matches("Excercise[\\d]_[\\d]")) {
                    s2 = s.substring(0, s.indexOf("_") - 1) + "0" + s.substring(s.indexOf("_") - 1, s.indexOf("_") + 1)
                            + "0" + s.substring(s.indexOf("_") + 1);
                    System.out.println("A word has been changed in " + file.getName());
                }
                content.append(s2 + " ");
            }
            content.append("\r\n");
        }
        input.close();
        PrintWriter output = new PrintWriter(file);
        output.print(content);
        output.close();
    }
}
