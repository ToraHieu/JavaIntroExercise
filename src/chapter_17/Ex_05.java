package chapter_17;

import java.io.*;
import java.util.Date;

public class Ex_05 {
    public static void main(String[] args) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_05.dat"))) {
            int[] arr = {1, 2, 3, 4, 5, 6};
            output.writeObject(arr);
            output.writeObject(new Date());
            output.writeDouble(10.5);
        }
    }
}
