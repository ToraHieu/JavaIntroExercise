package chapter_17;

import java.io.*;

public class Ex_12 {
    public static void main(String[] args) throws IOException {
        /* Reserved code for testing purpose
        // Stimulate command input, assuming all input is valid
        args = new String[]{"SourceFile.txt.1", "SourceFile.txt.2", "SourceFile.txt.3", "TargetFile.txt"};*/

        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[args.length - 1]))) {
            for (int i = 0; i < args.length - 1; i++) {
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(args[i]))) {
                    byte[] bytes = new byte[input.available()];
                    //noinspection ResultOfMethodCallIgnored
                    input.read(bytes);
                    output.write(bytes);
                }
            }
        }
    }
}
