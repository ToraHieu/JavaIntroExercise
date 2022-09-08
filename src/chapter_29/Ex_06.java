package chapter_29;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Ex_06 {
    public static void main(String[] args) throws IOException {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("WeightedTailModel16.dat"))) {
            output.writeObject(new WeightedTailModel16());
        }
    }
}


