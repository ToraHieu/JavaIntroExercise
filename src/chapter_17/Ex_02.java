package chapter_17;

import java.io.*;

public class Ex_02 {
    public static void main(String[] args) throws IOException {
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Exercise17_03.dat", false)))) {
            for (int i = 0; i < 150; i++)
                output.writeDouble(Math.random() * 100);
        }
    }
}
