package chapter_17;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CkPts_4_12 {
    public static void main(String[] args) throws IOException {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream("t.txt"))) {
            output.writeInt(1234);
            output.writeInt(5678);
        }
    }
}
