package chapter_17;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ex_07 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Exercise17_06.dat"))) {
            try {
                //noinspection InfiniteLoopStatement
                while (true) {
                    System.out.println(((SerializableLoan)input.readObject()).getTotalPayment());
                }
            } catch (EOFException ex) {
                System.out.print("End of file.");
            }
        }
    }
}
