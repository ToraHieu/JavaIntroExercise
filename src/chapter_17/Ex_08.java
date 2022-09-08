package chapter_17;

import java.io.*;

public class Ex_08 {
    public static void main(String[] args) throws IOException {
        int count;
        File file = new File("Exercise17_08.dat");
        if (file.exists()) {
            try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
                count = input.readInt();
            }
        } else {
            count = 0;
        }

        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file))) {
            output.writeInt(++count);
        }
    }
}
