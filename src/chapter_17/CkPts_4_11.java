package chapter_17;

import java.io.*;

public class CkPts_4_11 {
    public static void main(String[] args) throws IOException {
        try (PrintWriter output = new PrintWriter("t.txt")) {
            output.printf("%s", "1234");
            output.printf("%s", "5678");
        }
    }
}
